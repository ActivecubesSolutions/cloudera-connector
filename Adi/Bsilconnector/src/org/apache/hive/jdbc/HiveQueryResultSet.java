package org.apache.hive.jdbc;

import static org.apache.hive.service.cli.thrift.TCLIServiceConstants.TYPE_NAMES;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hive.service.cli.TableSchema;
import org.apache.hive.service.cli.thrift.TCLIService;
import org.apache.hive.service.cli.thrift.TColumnDesc;
import org.apache.hive.service.cli.thrift.TFetchOrientation;
import org.apache.hive.service.cli.thrift.TFetchResultsReq;
import org.apache.hive.service.cli.thrift.TFetchResultsResp;
import org.apache.hive.service.cli.thrift.TGetResultSetMetadataReq;
import org.apache.hive.service.cli.thrift.TGetResultSetMetadataResp;
import org.apache.hive.service.cli.thrift.TOperationHandle;
import org.apache.hive.service.cli.thrift.TRow;
import org.apache.hive.service.cli.thrift.TSessionHandle;
import org.apache.hive.service.cli.thrift.TTableSchema;

import com.test.HiveJdbcClient;

/**
 * HiveQueryResultSet.
 *
 */
public class HiveQueryResultSet extends HiveBaseResultSet {

    public static final Log LOG = LogFactory.getLog(HiveQueryResultSet.class);

	public static final int executorThreads = HiveJdbcClient.EXECUTOR_CONSUMER_THREADS;

    private TCLIService.Iface client;
    private TOperationHandle stmtHandle;
    private TSessionHandle sessHandle;
    private int maxRows;
    private int fetchSize;
    private int rowsFetched = 0;
    private HiveConnection hiveConn = null;
    private boolean needsExtendedDiagnostics = false;

    private List<TRow> fetchedRows;
    private Iterator<TRow> fetchedRowsItr;
    private boolean isClosed = false;
    private boolean emptyResultSet = false;
    private boolean isScrollable = false;
    private boolean fetchFirst = false;
    private boolean completed = true;
    private int noOfRowsInQueue = 0;
    private volatile int noOfRowsConsumed = 0;

    public static class Builder {

        private TCLIService.Iface client = null;
        private TOperationHandle stmtHandle = null;
        private TSessionHandle sessHandle = null;
        private HiveConnection hiveConn = null;
        private boolean needsExtendedDiagnostics = false;

        /**
         * Sets the limit for the maximum number of rows that any ResultSet
         * object produced by this Statement can contain to the given number. If
         * the limit is exceeded, the excess rows are silently dropped. The
         * value must be >= 0, and 0 means there is not limit.
         */
        private int maxRows = 0;
        private boolean retrieveSchema = true;
        private List<String> colNames;
        private List<String> colTypes;
        private int fetchSize = 50;
        private boolean emptyResultSet = false;
        private boolean isScrollable = false;

        public Builder setClient(TCLIService.Iface client) {
            this.client = client;
            return this;
        }

        public Builder setStmtHandle(TOperationHandle stmtHandle) {
            this.stmtHandle = stmtHandle;
            return this;
        }

        public Builder setHiveConn(HiveConnection hiveConn) {
            this.hiveConn = hiveConn;
            return this;
        }

        public Builder setNeedExtendedDiagnostics(
                boolean needExtendedDiagnostics) {
            this.needsExtendedDiagnostics = needExtendedDiagnostics;
            return this;
        }

        public Builder setSessionHandle(TSessionHandle sessHandle) {
            this.sessHandle = sessHandle;
            return this;
        }

        public Builder setMaxRows(int maxRows) {
            this.maxRows = maxRows;
            return this;
        }

        public Builder setSchema(List<String> colNames, List<String> colTypes) {
            this.colNames = new ArrayList<String>();
            this.colNames.addAll(colNames);
            this.colTypes = new ArrayList<String>();
            this.colTypes.addAll(colTypes);
            this.retrieveSchema = false;
            return this;
        }

        public Builder setFetchSize(int fetchSize) {
            this.fetchSize = fetchSize;
            return this;
        }

        public Builder setEmptyResultSet(boolean emptyResultSet) {
            this.emptyResultSet = emptyResultSet;
            return this;
        }

        public Builder setScrollable(boolean setScrollable) {
            this.isScrollable = setScrollable;
            return this;
        }

        public HiveQueryResultSet build() throws SQLException {
            return new HiveQueryResultSet(this);
        }
    }

    protected HiveQueryResultSet(Builder builder) throws SQLException {
        this.client = builder.client;
        this.stmtHandle = builder.stmtHandle;
        this.sessHandle = builder.sessHandle;
        this.hiveConn = builder.hiveConn;
        this.needsExtendedDiagnostics = builder.needsExtendedDiagnostics;
        this.fetchSize = builder.fetchSize;
        columnNames = new ArrayList<String>();
        columnTypes = new ArrayList<String>();
        if (builder.retrieveSchema) {
            retrieveSchema();
        } else {
            this.columnNames.addAll(builder.colNames);
            this.columnTypes.addAll(builder.colTypes);
        }
        this.emptyResultSet = builder.emptyResultSet;
        if (builder.emptyResultSet) {
            this.maxRows = 0;
        } else {
            this.maxRows = builder.maxRows;
        }
        this.isScrollable = builder.isScrollable;
        if (needsExtendedDiagnostics && hiveConn == null) {
            throw new SQLException(
                    "Internal error: "
                            + "Resutlset needs connection object to load extended diagnostisc");
        }
    }

    /**
     * Retrieve schema from the server
     */
    private void retrieveSchema() throws SQLException {
        try {
            TGetResultSetMetadataReq metadataReq = new TGetResultSetMetadataReq(
                    stmtHandle);
            // TODO need session handle
            TGetResultSetMetadataResp metadataResp = client
                    .GetResultSetMetadata(metadataReq);
            Utils.verifySuccess(metadataResp.getStatus());

            StringBuilder namesSb = new StringBuilder();
            StringBuilder typesSb = new StringBuilder();

            TTableSchema schema = metadataResp.getSchema();
            if (schema == null || !schema.isSetColumns()) {
                // TODO: should probably throw an exception here.
                return;
            }
            setSchema(new TableSchema(schema));

            List<TColumnDesc> columns = schema.getColumns();
            for (int pos = 0; pos < schema.getColumnsSize(); pos++) {
                if (pos != 0) {
                    namesSb.append(",");
                    typesSb.append(",");
                }
                String columnName = columns.get(pos).getColumnName();
                columnNames.add(columnName);
                String columnTypeName = TYPE_NAMES.get(columns.get(pos)
                        .getTypeDesc().getTypes().get(0).getPrimitiveEntry()
                        .getType());
                columnTypes.add(columnTypeName);
            }
        } catch (SQLException eS) {
            throw eS; // rethrow the SQLException as is
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SQLException("Could not create ResultSet: "
                    + ex.getMessage(), ex);
        }
    }

    /**
     * Set the specified schema to the resultset
     *
     * @param colNames
     * @param colTypes
     */
    public void setSchema(List<String> colNames, List<String> colTypes) {
        columnNames.addAll(colNames);
        columnTypes.addAll(colTypes);
    }

    @Override
    public void close() throws SQLException {
        // Need reset during re-open when needed
        client = null;
        stmtHandle = null;
        sessHandle = null;
        isClosed = true;
    }

    /**
     * Moves the cursor down one row from its current position.
     *
     * @see java.sql.ResultSet#next()
     * @throws SQLException
     *             if a database access error occurs.
     */
    public boolean next() throws SQLException {
        // List<Object> finalrow = new ArrayList<Object>();
        fetchedDeque = new LinkedBlockingDeque<List<TRow>>(HiveJdbcClient.FETCHED_QUEUE_SIZE);
        fetchedResultQueue = new LinkedBlockingDeque<String>(HiveJdbcClient.FETCHED_QUEUE_SIZE*2);

        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        if (emptyResultSet || (maxRows > 0 && rowsFetched >= maxRows)) {
            return false;
        }

        new Thread() {

            public void run() {
                while (completed) {
                    try {
                        TFetchOrientation orientation = TFetchOrientation.FETCH_NEXT;
                        if (fetchFirst) {
                            // If we are asked to start from begining, clear the
                            // current fetched resultset
                            orientation = TFetchOrientation.FETCH_FIRST;
                            fetchedRows = null;
                            fetchedRowsItr = null;
                            fetchFirst = false;
                        }

                        if (fetchedRows == null || true) {
                            TFetchResultsReq fetchReq = new TFetchResultsReq(
                                    stmtHandle, orientation, fetchSize);
                            TFetchResultsResp fetchResp = client
                                    .FetchResults(fetchReq);
                            if (needsExtendedDiagnostics) {
                                hiveConn.loadExtendedErrorMsg(stmtHandle,
                                        fetchResp.getStatus());
                            }

                            Utils.verifySuccessWithInfo(fetchResp.getStatus());
                            fetchedRows = fetchResp.getResults().getRows();

                            fetchedRowsItr = fetchedRows.iterator();

                        }

                        if (!fetchedRowsItr.hasNext()) {
                            completed = false;

                        } else {
                            fetchedDeque.put(fetchedRows);
                            noOfRowsInQueue++;
                        }

                        rowsFetched = rowsFetched + fetchSize;

                    } catch (SQLException eS) {
                        try {
                            throw eS;
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        try {
                            throw new SQLException("Error retrieving next row",
                                    ex);
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        final ExecutorService executorService = Executors.newFixedThreadPool(executorThreads);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (fetchedDeque.size() > 0) {
        	System.out.println("Current fetchedDeque size is :"+fetchedDeque.size());
            executorService.execute(new Runnable() {
                public void run() {
                    if (fetchedDeque.remainingCapacity() == HiveJdbcClient.FETCHED_QUEUE_SIZE*2) {
                        executorService.shutdown();
                    }

                    List<List<TRow>> initialList = new ArrayList<List<TRow>>();
                    //System.out.println(Thread.currentThread().getName());
                    StringBuilder finalrow = new StringBuilder(100);
                    try {
						fetchedRowsItr= fetchedDeque.take().iterator();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                   
                        rowcsv = "";
                        finalrow.append("[");
                        while (fetchedRowsItr.hasNext()) {

                            row = fetchedRowsItr.next();

                            StringBuilder rowout = new StringBuilder(100);

                            for (int i = 1; i <= columnNames.size(); i++) {
                                String colName = columnNames.get(i - 1);
                                String colValue = "null";
                                try {
                                    if (getColumnValue(i) != null) {
                                        colValue = getColumnValue(i).toString();
                                        if (colValue.contains("\\")) {
                                            colValue = colValue.replaceAll(
                                                    "\\", "&#92;");
                                        } else {
                                            colValue = colValue.replaceAll(
                                                    "\"", "&#34;");
                                        }
                                    }
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                colName = colName.replaceAll("\"", "&#34;");

                                if (columnNames.size() == 1) {

                                    rowout.append("{");
                                    rowout.append("\"");
                                    rowout = rowout.append(colName);
                                    rowout = rowout.append("\"");
                                    rowout = rowout.append(":");
                                    rowout = rowout.append("\"");

                                    rowout = rowout.append(colValue);
                                    rowout = rowout.append("\"");
                                    rowout.append("}");
                                    rowout = rowout.append(",");
                                } else {

                                    if (i == columnNames.size()) {
                                        rowout.append("\"");
                                        rowout = rowout.append(colName);
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(":");
                                        rowout = rowout.append("\"");

                                        rowout = rowout.append(colValue);
                                        rowout = rowout.append("\"");
                                        rowout.append("}");
                                        rowout = rowout.append(",");
                                    } else if (i == 1) {
                                        rowout.append("{");
                                        rowout.append("\"");
                                        rowout = rowout.append(colName);
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(":");
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(colValue);
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(",");
                                    } else {
                                        rowout.append("\"");
                                        rowout = rowout.append(colName);
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(":");
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(colValue);
                                        rowout = rowout.append("\"");
                                        rowout = rowout.append(",");
                                    }
                                }
                            }

                            // rowout.append("\"");
                            finalrow.append(rowout);

                            /* rowout=null; */
                        }

                        // finalrow.deleteCharAt(finalrow.lastIndexOf(","));

                        finalrow.append("]");
                        try {
                            fetchedResultQueue.put(finalrow.toString());

                            // System.out.println("added into Queue "+fetchedRseultQueque.size());
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    

                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        try {
            fetchedResultQueue.put("false");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        // System.out.println("Finished all threads");
        String rowStr = "";

        if (LOG.isDebugEnabled()) {
            LOG.debug("Fetched row string: " + rowStr);
        }

        // NOTE: fetchOne dosn't throw new SQLException("Method not supported").
        return true;
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        return super.getMetaData();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        fetchSize = rows;
    }

    @Override
    public int getType() throws SQLException {
        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        if (isScrollable) {
            return ResultSet.TYPE_SCROLL_INSENSITIVE;
        } else {
            return ResultSet.TYPE_FORWARD_ONLY;
        }
    }

    @Override
    public int getFetchSize() throws SQLException {
        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        return fetchSize;
    }

    /**
     * Moves the cursor before the first row of the resultset.
     *
     * @see java.sql.ResultSet#next()
     * @throws SQLException
     *             if a database access error occurs.
     */
    @Override
    public void beforeFirst() throws SQLException {
        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        if (!isScrollable) {
            throw new SQLException(
                    "Method not supported for TYPE_FORWARD_ONLY resultset");
        }
        fetchFirst = true;
        rowsFetched = 0;
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        if (isClosed) {
            throw new SQLException("Resultset is closed");
        }
        return (rowsFetched == 0);
    }

    @Override
    public int getRow() throws SQLException {
        return rowsFetched;
    }

    public <T> T getObject(String columnLabel, Class<T> type)
            throws SQLException {
        // JDK 1.7
        throw new SQLException("Method not supported");
    }

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        // JDK 1.7
        throw new SQLException("Method not supported");
    }
}
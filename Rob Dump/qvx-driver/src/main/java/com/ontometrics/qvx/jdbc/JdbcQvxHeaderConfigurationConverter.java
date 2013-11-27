package com.ontometrics.qvx.jdbc;

import com.ontometrics.qvx.writer.QvxHeaderConfigurationConverter;
import com.ontometrics.qvx.logging.LogUtil;
import com.ontometrics.qvx.writer.WriterConstants;

import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Implementation of {@link com.ontometrics.qvx.writer.QvxHeaderConfigurationConverter} from {@link java.sql.ResultSetMetaData}
 * JdbcQvxHeaderConfigurationConverter.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class JdbcQvxHeaderConfigurationConverter implements QvxHeaderConfigurationConverter<ResultSetMetaData> {

    private static final String DEFAULT_TABLE_NAME = "QVX_RESULT_SET";

    @Override
    public JdbcHeaderConfiguration convert(ResultSetMetaData resultSetMetaData) throws SQLException {
        Context context = new Context(resultSetMetaData);
        String tableName = resolveMetadata(context);
        if (tableName == null || tableName.isEmpty()) {
            tableName = DEFAULT_TABLE_NAME;
        }

        return new JdbcHeaderConfiguration(tableName, context.getFieldNames(),
                WriterConstants.CHARSET_UTF_8, true, new BigInteger("1"), new BigInteger("0"), context.getFieldTypes(),
                context.getValueAccessorList());
    }

    /**
     * @param context Context
     * @return If all columns have the same table name, return it, otherwise return null
     */
    private String resolveMetadata(Context context) throws SQLException {
        Set<String> tableNameSet = new HashSet<String>(context.getColumnCount());
        ResultSetMetaData resultSetMetaData = context.getResultSetMetaData();

        for (int i = 1; i <= context.getColumnCount(); ++i) {
            int columnType = resultSetMetaData.getColumnType(i);
            JdbcValueAccessor fieldValueAccessor = getFieldValueAccessor(columnType, i);
            if (fieldValueAccessor != null) {
                tableNameSet.add(resultSetMetaData.getTableName(i));
                context.addField(resultSetMetaData.getColumnName(i), fieldValueAccessor);
            } else {
                LogUtil.logMessage("Column columnType " + columnType + " is not supported");
            }
        }
        return tableNameSet.size() == 1 ? tableNameSet.iterator().next() : null;
    }

    private JdbcValueAccessor getFieldValueAccessor(int columnType, int columnIndex) {
        switch (columnType) {
            case Types.BIT:
            case Types.BOOLEAN:
                return new BooleanJdbcValueAccessor(columnIndex);
            case Types.TINYINT:
                return new ByteJdbcValueAccessor(columnIndex);
            case Types.SMALLINT:
                return new ShortJdbcValueAccessor(columnIndex);
            case Types.INTEGER:
                return new IntegerJdbcValueAccessor(columnIndex);
            case Types.BIGINT:
                return new LongJdbcValueAccessor(columnIndex);
            case Types.REAL:
                return new FloatJdbcValueAccessor(columnIndex);
            case Types.FLOAT:
            case Types.DOUBLE:
                return new DoubleJdbcValueAccessor(columnIndex);
            case Types.DECIMAL:
            case Types.NUMERIC:
                return new BigDecimalJdbcValueAccessor(columnIndex);
            case Types.DATE:
                return new JdbcValueAccessor<Date>(columnIndex, Date.class);
            case Types.TIME:
                return new JdbcValueAccessor<Time>(columnIndex, Time.class);
            case Types.TIMESTAMP:
                return new JdbcValueAccessor<Timestamp>(columnIndex, Timestamp.class);
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
            case Types.CLOB:
                return new StringJdbcValueAccessor(columnIndex);
            case Types.NCHAR:
            case Types.NVARCHAR:
            case Types.LONGNVARCHAR:
                return new NStringJdbcValueAccessor(columnIndex);
        }
        return null;
    }


    private static class Context {
        private final ResultSetMetaData resultSetMetaData;
        private final int columnCount;
        private final List<String> fieldNames;
        private final List<Class> fieldTypes;
        private final List<JdbcValueAccessor> valueAccessorList;

        public Context(ResultSetMetaData resultSetMetaData) throws SQLException {
            this.resultSetMetaData = resultSetMetaData;
            this.columnCount = resultSetMetaData.getColumnCount();
            this.fieldNames = new ArrayList<String>(columnCount);
            this.fieldTypes = new ArrayList<Class>(columnCount);
            this.valueAccessorList = new ArrayList<JdbcValueAccessor>(columnCount);
        }

        private ResultSetMetaData getResultSetMetaData() {
            return resultSetMetaData;
        }

        private int getColumnCount() {
            return columnCount;
        }

        public void addField(String name, JdbcValueAccessor valueAccessor) {
            if (name == null || name.length() == 0) {
                fieldNames.add("Column " + (fieldNames.size() + 1));
            } else {
                fieldNames.add(name);
            }
            fieldTypes.add(valueAccessor.getValueClass());
            valueAccessorList.add(valueAccessor);
        }

        private List<String> getFieldNames() {
            return fieldNames;
        }

        public List<Class> getFieldTypes() {
            return fieldTypes;
        }

        private List<JdbcValueAccessor> getValueAccessorList() {
            return valueAccessorList;
        }
    }
}

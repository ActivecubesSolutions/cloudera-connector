package com.ontometrics.qvx.jdbc;

import com.ontometrics.qvx.writer.HeaderConfiguration;
import com.ontometrics.qvx.writer.QvxDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * JdbcQvxDatasource.java
 * Created on 10 10, 2013 by Andrey Chorniy
 */
public class JdbcQvxDatasource implements QvxDataSource {
    private JdbcHeaderConfiguration headerConfiguration;
    private ResultSet resultSet;

    public JdbcQvxDatasource(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        this.headerConfiguration = new JdbcQvxHeaderConfigurationConverter().convert(resultSet.getMetaData());
    }

    @Override
    public HeaderConfiguration geHeaderConfiguration() {
        return headerConfiguration;
    }

    @Override
    public Iterator<Object[]> getDataIterator() {
        return new ResultSetIterator();
    }

    public class ResultSetIterator implements Iterator <Object[]>{

        private List<JdbcValueAccessor> accessors =  headerConfiguration.getValueAccessorList();

        private boolean hasNext = false;
        private boolean nextChecked = false;

        public Object [] next(){
            if (!nextChecked) {
                gotoNext();
            }
            nextChecked = false;
            return readRowData();
        }

        public boolean hasNext(){
            if (!nextChecked) {
                hasNext = gotoNext();
                nextChecked = true;
            }
            return hasNext;
        }

        private boolean gotoNext(){
            try {
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove()");
        }

        private Object [] readRowData() {
            Object [] result = new Object[accessors.size()];
            for (int i = 0; i < accessors.size(); i++) {
                JdbcValueAccessor jdbcValueAccessor = accessors.get(i);
                try {
                    result[i] =  jdbcValueAccessor.getValue(resultSet);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return result;
        }

    }
}

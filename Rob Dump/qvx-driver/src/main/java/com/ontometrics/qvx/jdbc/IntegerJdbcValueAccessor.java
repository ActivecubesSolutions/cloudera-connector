package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * IntegerJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class IntegerJdbcValueAccessor extends JdbcValueAccessor<Integer> {
    @Override
    protected Integer resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(getColumnIndex());
    }

    protected IntegerJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

}

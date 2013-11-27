package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StringJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class StringJdbcValueAccessor extends JdbcValueAccessor<String> {
    @Override
    protected String resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getString(getColumnIndex());
    }

    public StringJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }
}

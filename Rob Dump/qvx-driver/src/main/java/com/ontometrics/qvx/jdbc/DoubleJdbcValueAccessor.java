package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DoubleJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class DoubleJdbcValueAccessor extends JdbcValueAccessor<Double> {
    protected DoubleJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

    @Override
    protected Double resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getDouble(getColumnIndex());
    }
}

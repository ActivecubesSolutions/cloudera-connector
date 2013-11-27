package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FloatJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class FloatJdbcValueAccessor extends JdbcValueAccessor<Float> {
    protected FloatJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

    @Override
    protected Float resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getFloat(getColumnIndex());
    }
}

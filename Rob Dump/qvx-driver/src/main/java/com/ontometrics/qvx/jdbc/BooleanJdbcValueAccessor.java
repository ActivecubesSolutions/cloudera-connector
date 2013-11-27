package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BooleanJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class BooleanJdbcValueAccessor extends JdbcValueAccessor<Boolean> {
    @Override
    protected Boolean resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getBoolean(getColumnIndex());
    }

    protected BooleanJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

}

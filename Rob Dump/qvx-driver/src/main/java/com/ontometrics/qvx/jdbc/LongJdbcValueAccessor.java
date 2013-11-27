package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LongJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class LongJdbcValueAccessor extends JdbcValueAccessor<Long> {
    @Override
    protected Long resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getLong(getColumnIndex());
    }

    protected LongJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

}

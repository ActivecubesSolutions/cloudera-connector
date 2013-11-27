package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ShortJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class ShortJdbcValueAccessor extends JdbcValueAccessor<Short> {
    @Override
    protected Short resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getShort(getColumnIndex());
    }

    protected ShortJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

}

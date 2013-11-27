package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ByteJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class ByteJdbcValueAccessor extends JdbcValueAccessor<Byte> {
    @Override
    protected Byte resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getByte(getColumnIndex());
    }

    protected ByteJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

}

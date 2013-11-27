package com.ontometrics.qvx.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * NStringJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 * Copyright 2011, itMD, LLC. All Rights Reserved.
 */
public class NStringJdbcValueAccessor extends JdbcValueAccessor<String> {
    @Override
    protected String resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getNString(getColumnIndex());
    }

    public NStringJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }
}

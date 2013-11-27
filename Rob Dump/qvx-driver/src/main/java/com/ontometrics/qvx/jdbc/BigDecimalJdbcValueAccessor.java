package com.ontometrics.qvx.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BigDecimalJdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class BigDecimalJdbcValueAccessor extends JdbcValueAccessor<BigDecimal> {
    protected BigDecimalJdbcValueAccessor(int columnIndex) {
        super(columnIndex);
    }

    @Override
    protected BigDecimal resolveValue(ResultSet resultSet) throws SQLException {
        return resultSet.getBigDecimal(getColumnIndex());
    }
}

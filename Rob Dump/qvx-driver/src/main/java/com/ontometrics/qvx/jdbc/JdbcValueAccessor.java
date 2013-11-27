package com.ontometrics.qvx.jdbc;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcValueAccessor.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class JdbcValueAccessor<T> {
    protected T resolveValue(ResultSet resultSet) throws SQLException {
        return (T)resultSet.getObject(columnIndex);
    }

    public T getValue(ResultSet resultSet) throws SQLException {
        T value = resolveValue(resultSet);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    private int columnIndex;
    private Class<T> valueClass;

    protected JdbcValueAccessor(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public JdbcValueAccessor(int columnIndex, Class<T> valueClass) {
        this.columnIndex = columnIndex;
        this.valueClass = valueClass;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Class<T> getValueClass() {
        if (valueClass == null) {
            valueClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return valueClass;
    }
}

package com.ontometrics.qvx.jdbc;

import com.ontometrics.qvx.writer.HeaderConfiguration;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;

/**
 * JdbcHeaderConfiguration.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class JdbcHeaderConfiguration extends HeaderConfiguration {
    private List<JdbcValueAccessor> valueAccessorList;

    public JdbcHeaderConfiguration(String tableName, List<String> fieldNames, Charset charset, boolean qvxDualFormat,
                                   BigInteger majorVersion, BigInteger minorVersion, List<Class> fieldTypes,
                                   List<JdbcValueAccessor> valueAccessorList) {
        super(tableName, fieldNames, charset, qvxDualFormat, majorVersion, minorVersion, fieldTypes);
        this.valueAccessorList = valueAccessorList;
    }

    public List<JdbcValueAccessor> getValueAccessorList() {
        return valueAccessorList;
    }
}

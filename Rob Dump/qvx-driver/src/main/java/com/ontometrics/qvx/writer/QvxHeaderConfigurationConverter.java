package com.ontometrics.qvx.writer;

import java.sql.SQLException;

/**
 * QvxHeaderConfigurationConverter.java
 * Created on 10 07, 2013 by Andrey Chorniy
 */
public interface QvxHeaderConfigurationConverter<T>{
    HeaderConfiguration convert (T input) throws SQLException;
}

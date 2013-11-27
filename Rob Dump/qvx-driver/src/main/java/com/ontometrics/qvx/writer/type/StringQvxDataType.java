package com.ontometrics.qvx.writer.type;

import com.ontometrics.qvx.writer.WriterConstants;

/**
* StringQvxDataType.java
* Created on 10 09, 2013 by Andrey Chorniy
*/
public class StringQvxDataType extends BaseQvxDataType<String> {

    public double toDouble(String number) {
        return WriterConstants.zeroDouble;
    }

    protected String convertToString(String value) {
        return value;
    }

    public boolean canHandle(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }
}

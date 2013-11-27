package com.ontometrics.qvx.writer.type;

/**
* NumberQvxDataType.java
* Created on 10 09, 2013 by Andrey Chorniy
*/
public class NumberQvxDataType extends BaseQvxDataType<Number> {

    public double toDouble(Number number) {
        return number.doubleValue();
    }

    protected String convertToString(Number value) {
        return value.toString();
    }

    public boolean canHandle(Class clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

}

package com.ontometrics.qvx.writer;

/**
 * FieldDescriptor.java
 * Created on 10 09, 2013 by Andrey Chorniy
 */
public class FieldDescriptor <T>{

    private String fieldName;

    private QvxDataType<T> qvxDataType;

    public FieldDescriptor(String fieldName, QvxDataType<T> qvxDataType) {
        this.fieldName = fieldName;
        this.qvxDataType = qvxDataType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public QvxDataType<T> getDataType() {
        return qvxDataType;
    }
}

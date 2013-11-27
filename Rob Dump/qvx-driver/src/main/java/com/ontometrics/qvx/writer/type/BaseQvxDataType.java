package com.ontometrics.qvx.writer.type;

import com.ontometrics.qvx.writer.QvxDataType;

import java.nio.charset.Charset;

/**
* BaseQvxDataType.java
* Created on 10 09, 2013 by Andrey Chorniy
*/
abstract class BaseQvxDataType<T> implements QvxDataType<T> {

    public byte[] getTextRepresentation(T value, Charset charset) {
        String data = convertToString(value);
        StringBuilder sb = new StringBuilder(data.length()+1);
        sb.append(data).append("\0");
        return sb.toString().getBytes(charset);
    }

    protected abstract String convertToString(T value);
}

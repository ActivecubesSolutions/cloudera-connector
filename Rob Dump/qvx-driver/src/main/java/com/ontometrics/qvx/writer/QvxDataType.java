package com.ontometrics.qvx.writer;

import java.nio.charset.Charset;

/**
 *
 * @param <T>
 */
public interface QvxDataType<T>{
    /**
     *
     * @param clazz class to test
     * @return true if this QvxDataType instance is handling the clazz
     */
    boolean canHandle(Class clazz);

    double toDouble(T value);

    /**
     * @param value value to convert
     * @return string representation of value
     */
    byte[] getTextRepresentation(T value, Charset charset);
}

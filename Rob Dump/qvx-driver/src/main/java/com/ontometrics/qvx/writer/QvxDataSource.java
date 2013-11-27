package com.ontometrics.qvx.writer;

import java.util.Iterator;

/**
 * QvxDataSource.java
 * Created on 10 10, 2013 by Andrey Chorniy
 */
public interface QvxDataSource {

    HeaderConfiguration geHeaderConfiguration();

    Iterator <Object[]> getDataIterator();
}

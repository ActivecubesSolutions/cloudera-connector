package com.ontometrics.qvx.writer;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.TimeZone;

/**
 * WriterConstants.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public interface WriterConstants {
    TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    Charset CHARSET_UTF_16 = Charset.forName("UTF-16");
    BigInteger MAJOR_VERSION = new BigInteger("1");
    BigInteger MINOR_VERSION = new BigInteger("0");
    double zeroDouble = 0.0;
}

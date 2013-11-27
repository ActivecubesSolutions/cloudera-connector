package com.ontometrics.qvx.writer;

import java.math.BigInteger;

/**
 * CodePageFactory.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public class CodePageFactory {
    private static final CodePageFactory instance = new CodePageFactory();

    public static CodePageFactory instance() {
        return instance;
    }

    public BigInteger createCodePage(HeaderConfiguration headerConfiguration) {
        if (headerConfiguration.getCharset().equals(WriterConstants.CHARSET_UTF_16)) {
            if (headerConfiguration.isBigEndian()) {
                return new BigInteger("1201");
            }
            return new BigInteger("1200");
        }
        return null;
    }
}

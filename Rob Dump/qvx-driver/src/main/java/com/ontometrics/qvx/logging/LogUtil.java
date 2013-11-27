package com.ontometrics.qvx.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * LogUtil.java
 * Created on 10/14/2013 by Nikolay Chorniy
 */
public class LogUtil {
    public static void logMessage(String msg){
        Calendar cal = Calendar.getInstance();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.LONG);
        System.out.println(df.format(cal.getTime()) + ": " + msg);
    }

    public static void logError(String msg, Throwable throwable) {
        Calendar cal = Calendar.getInstance();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.LONG);
        StringBuilder builder = new StringBuilder(df.format(cal.getTime()) + ": " + msg );
        if (throwable != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter exceptionWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(exceptionWriter);
            exceptionWriter.close();
            builder.append(":\n").append(stringWriter.toString());
        }
        System.err.println(builder.toString());
    }

}

package com.ontometrics.qvx.writer.type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
* DateQvxDataType.java
* Created on 10 09, 2013 by Andrey Chorniy
*/
public class DateQvxDataType extends BaseQvxDataType<Date> {
    private TimeZone timeZone;

    private SimpleDateFormat simpleDateFormat;

    public DateQvxDataType(TimeZone timeZone, String dateFormat) {
        this(timeZone, new SimpleDateFormat(dateFormat));
    }

    public DateQvxDataType(TimeZone timeZone, SimpleDateFormat simpleDateFormat) {
        this.timeZone = timeZone;
        this.simpleDateFormat = simpleDateFormat;
    }


    public double toDouble(Date  date) {
        return toDouble(date, timeZone);
    }

    protected String convertToString(Date value) {
        return simpleDateFormat.format(value);
    }

    public boolean canHandle(Class clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    private static double toDouble(Date date, TimeZone timeZone) {
        //see http://stackoverflow.com/questions/2666112/convert-miliseconds-to-date-in-excel
        if(timeZone == null) {
            return ((double) date.getTime()) / 86400000D + 25569D;
        } else {
            return ((double) date.getTime() + (double) timeZone.getRawOffset()) / 86400000D + 25569D;
        }
    }

    private void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}

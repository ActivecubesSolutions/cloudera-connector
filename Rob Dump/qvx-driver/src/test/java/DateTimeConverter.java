import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* DateTimeConverter.java
* Created on 10/08/2013 by Nikolay Chorniy
*/
class DateTimeConverter extends ValueConverter<Date> {
    @Override
    public Date convert(String value) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
    }
}

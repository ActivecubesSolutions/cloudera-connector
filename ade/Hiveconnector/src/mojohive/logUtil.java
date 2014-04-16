package mojohive;

import java.text.DateFormat;
import java.util.Calendar;

public class logUtil {

	public static void LogMsg(String msg){
		Calendar cal = Calendar.getInstance();
	    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,
	        DateFormat.LONG);
	    System.out.println(df.format(cal.getTime()) + ": " + msg);
	}
}

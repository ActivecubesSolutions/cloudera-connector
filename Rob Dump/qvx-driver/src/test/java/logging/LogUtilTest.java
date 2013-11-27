package logging;

import com.ontometrics.qvx.logging.LogUtil;
import org.junit.Test;

/**
 * LogUtilTest.java
 * Created on 10/14/2013 by Nikolay Chorniy
 */
public class LogUtilTest {

    @Test
    public void logErrorTest() {
        LogUtil.logError("Hi Guys", new Exception());
        LogUtil.logError("Hi Guys", null);
    }


}

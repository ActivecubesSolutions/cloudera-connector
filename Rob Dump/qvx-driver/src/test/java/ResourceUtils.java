import java.io.*;
import java.util.Properties;

import static org.junit.Assert.fail;

/**
 * ResourceUtils.java
 * Created on 10 11, 2013 by Andrey Chorniy
 */
public class ResourceUtils {

    public static Properties readDataSet(String name) throws IOException {
        Properties properties = new Properties();
        InputStream dataSetStream = null;
        try {
            dataSetStream = ResourceUtils.class.getResourceAsStream(name);
            properties.load(dataSetStream);
        } finally {
            if (dataSetStream != null) {
                dataSetStream.close();
            }
        }
        return properties;
    }

    public static OutputStream getOutputStream(Properties dataSet, String propertyName) throws FileNotFoundException {
        String out = dataSet.getProperty(propertyName);
        if (out.equals("console")) {
            return System.out;
        }
        File file = new File(out);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                fail("Failed to create file");
            }
        }
        return new FileOutputStream(out);
    }

}

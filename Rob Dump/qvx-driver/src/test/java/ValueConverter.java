import java.lang.reflect.ParameterizedType;

/**
 * Converts string from test dataset to a Java Object that {@link com.ontometrics.qvx.writer.QvxWriter} can handle
 * ValueConverter.java
 * Created on 10/08/2013 by Nikolay Chorniy
 */
public abstract class ValueConverter<T> {
    public abstract T convert(String value) throws Exception;
    private Class<T> valueClass;

    public Class<T> getValueClass() {
        if (valueClass == null) {
            valueClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return valueClass;
    }
}

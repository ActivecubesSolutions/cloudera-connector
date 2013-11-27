
/**
 * FloatConverter.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public class FloatConverter extends ValueConverter<Float> {
    @Override
    public Float convert(String value) throws Exception {
        return Float.valueOf(value);
    }
}

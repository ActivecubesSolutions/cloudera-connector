/**
 * DoubleConverter.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public class DoubleConverter extends ValueConverter<Double> {
    @Override
    public Double convert(String value) throws Exception {
        return Double.valueOf(value);
    }
}

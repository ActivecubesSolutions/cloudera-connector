/**
 * FieldType.java
 * Created on 10/08/2013 by Nikolay Chorniy
 */
public enum FieldType {
    INTEGER(new IntegerConverter()),
    FLOAT(new FloatConverter()),
    DOUBLE(new DoubleConverter()),
    TEXT(new StringConverter()),
    DATE(new DateConverter()),
    DATE_TIME(new DateTimeConverter());

    private final ValueConverter valueConverter;

    private FieldType(ValueConverter converter) {
        valueConverter = converter;
    }

    public ValueConverter getValueConverter() {
        return valueConverter;
    }
}
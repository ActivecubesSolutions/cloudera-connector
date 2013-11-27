/**
* IntegerConverter.java
* Created on 10/08/2013 by Nikolay Chorniy
*/
class IntegerConverter extends ValueConverter<Integer> {
    @Override
    public Integer convert(String value) {
        return Integer.valueOf(value);
    }
}

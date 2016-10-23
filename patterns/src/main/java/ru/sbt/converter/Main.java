package ru.sbt.converter;

import ru.sbt.converter.implementations.integer.IntegerToDoubleConverter;
import ru.sbt.converter.implementations.integer.IntegerToLongConverter;
import ru.sbt.converter.implementations.UniversalConverter;

/**
 * @author artem
 */
public class Main {

    public static void main(String[] args) {
        Converter converter = new UniversalConverter();
        converter.add(Integer.class, Long.class, new IntegerToLongConverter());
        converter.add(Integer.class, Double.class, new IntegerToDoubleConverter());
        Integer numberOfSomething = 10000;
        Long longValueOfSomething = converter.convert(numberOfSomething, Long.class);
        Double doubleValueOfSomething = converter.convert(numberOfSomething, Double.class);
        System.out.println(longValueOfSomething);
        System.out.println(doubleValueOfSomething);
    }
}

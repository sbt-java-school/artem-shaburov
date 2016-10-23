package ru.sbt.converter.implementations;

import ru.sbt.converter.Converter;
import ru.sbt.converter.ValueConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author artem
 */
public class UniversalConverter implements Converter {

    private Map<ConverterKey, ValueConverter<?, ?>> valueConverters = new HashMap<>();

    @Override
    public <T> T convert(Object value, Class<T> resultClass) {
        Class<?> originClass = value.getClass();
        ConverterKey converterKey = new ConverterKey(originClass, resultClass);
        if (!valueConverters.containsKey(converterKey)) {
            throw new IllegalArgumentException("No converter presented for classes [" + converterKey + "]. "
                    + "Add converter first.");
        }
        ValueConverter valueConverter = valueConverters.get(converterKey);
        @SuppressWarnings("unchecked") T convertedValue = (T) valueConverter.convert(value);
        return convertedValue;
    }

    @Override
    public void add(Class<?> originClass, Class<?> resultClass, ValueConverter valueConverter) {
        ConverterKey converterKey = new ConverterKey(originClass, resultClass);
        valueConverters.put(converterKey, valueConverter);
    }

    private class ConverterKey {

        private Class<?> originClass;
        private Class<?> resultClass;

        ConverterKey(Class<?> originClass, Class<?> resultClass) {
            this.originClass = originClass;
            this.resultClass = resultClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ConverterKey converterKey = (ConverterKey) o;

            return originClass.equals(converterKey.originClass) && resultClass.equals(converterKey.resultClass);
        }

        @Override
        public int hashCode() {
            int result = originClass.hashCode();
            result = 31 * result + resultClass.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return originClass + "|" + resultClass;
        }
    }
}

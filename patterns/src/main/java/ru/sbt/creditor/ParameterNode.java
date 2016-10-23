package ru.sbt.creditor;

import java.util.Map;

/**
 * @author artem
 */
public class ParameterNode implements Node {

    private String parameterName;

    public ParameterNode(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public double getResult(Map<String, Double> values) {
        return values.get(parameterName);
    }

    @Override
    public void add(Node node) {
        // ничего не делаем
    }
}

package ru.sbt.creditor;

import java.util.Map;

/**
 * @author artem
 */
public interface Node {
    double getResult(Map<String, Double> values);
    void add(Node node);
}

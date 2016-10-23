package ru.sbt.creditor.operations;

import ru.sbt.creditor.OperationNode;

import java.util.Map;

/**
 * @author artem
 */
public class EqualsNode extends OperationNode {
    @Override
    public double getResult(Map<String, Double> values) {
        checkNodes();
        double result = nodes.remove(0).getResult(values);
        return nodes.stream().allMatch(node -> node.getResult(values) == result) ? ONE : ZERO;
    }
}

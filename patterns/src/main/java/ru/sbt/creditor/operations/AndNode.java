package ru.sbt.creditor.operations;

import ru.sbt.creditor.OperationNode;

import java.util.Map;

/**
 * @author artem
 */
public class AndNode extends OperationNode {
    @Override
    public double getResult(Map<String, Double> values) {
        checkNodes();
        return nodes.stream().allMatch(node -> node.getResult(values) == ONE) ? ONE : ZERO;
    }
}

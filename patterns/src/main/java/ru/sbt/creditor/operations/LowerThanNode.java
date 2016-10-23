package ru.sbt.creditor.operations;

import ru.sbt.creditor.Node;
import ru.sbt.creditor.OperationNode;

import java.util.Map;

/**
 * @author artem
 */
public class LowerThanNode extends OperationNode {
    @Override
    public double getResult(Map<String, Double> values) {
        checkNodes();
        double currentNodeResult = nodes.remove(0).getResult(values);
        for (Node node : nodes) {
            double result = node.getResult(values);
            if (result < currentNodeResult) {
                return ZERO;
            }
            currentNodeResult = result;
        }
        return ONE;
    }
}

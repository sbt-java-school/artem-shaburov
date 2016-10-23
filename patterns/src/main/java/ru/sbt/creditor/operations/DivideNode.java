package ru.sbt.creditor.operations;

import ru.sbt.creditor.Node;
import ru.sbt.creditor.OperationNode;

import java.util.Map;

/**
 * @author artem
 */
public class DivideNode extends OperationNode {
    @Override
    public double getResult(Map<String, Double> values) {
        checkNodes();
        double result = nodes.remove(0).getResult(values);
        for (Node node : nodes) {
            result /= node.getResult(values);
        }
        return result;
    }
}

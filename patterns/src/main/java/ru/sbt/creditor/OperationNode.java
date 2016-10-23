package ru.sbt.creditor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 */
public abstract class OperationNode implements Node {

    protected List<Node> nodes = new ArrayList<>();
    protected static final double ZERO = 0d;
    protected static final double ONE = 1d;

    @Override
    public void add(Node node) {
        nodes.add(node);
    }

    protected void checkNodes() {
        if (nodes.isEmpty()) {
            throw new IllegalStateException("Nodes list must contains at least one node. Add nodes.");
        }
    }
}

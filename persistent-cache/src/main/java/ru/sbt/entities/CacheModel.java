package ru.sbt.entities;

import java.io.Serializable;

/**
 * Модель объекта из таблицы cache
 */
public class CacheModel implements Serializable {
    private Operation operation;
    private Object result;

    public CacheModel(Operation operation, Object result) {
        this.operation = operation;
        this.result = result;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getResult() {
        return result;
    }
}

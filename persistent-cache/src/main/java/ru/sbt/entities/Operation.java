package ru.sbt.entities;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Класс хранит {@link Object} delegate, {@link java.lang.reflect.Method} method и {@link Object}[] args
 */
public class Operation implements Serializable {
    private Object delegate;
    private Method method;
    private Object[] args;

    public Operation(Object delegate, Method method, Object[] args) {
        this.delegate = delegate;
        this.method = method;
        this.args = args;
    }
}

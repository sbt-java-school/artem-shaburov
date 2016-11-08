package com.artem.telegram.core;

import java.io.Serializable;

/**
 * @author artem
 */
public class Response<T> implements Serializable {

    private T body;

    public Response(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

}

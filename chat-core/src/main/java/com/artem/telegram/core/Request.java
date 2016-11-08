package com.artem.telegram.core;

import java.io.Serializable;

/**
 * @author artem
 */
public class Request implements Serializable {

    private RequestMethod requestMethod;
    private Object body;

    public Request(RequestMethod requestMethod, Object body) {
        this.requestMethod = requestMethod;
        this.body = body;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public Object getBody() {
        return body;
    }

    public boolean isAuthenticationRequest() {
        return requestMethod.equals(RequestMethod.AUTH);
    }

    public boolean isGetRequest() {
        return requestMethod.equals(RequestMethod.GET);
    }

    public boolean isPostRequest() {
        return requestMethod.equals(RequestMethod.POST);
    }

}

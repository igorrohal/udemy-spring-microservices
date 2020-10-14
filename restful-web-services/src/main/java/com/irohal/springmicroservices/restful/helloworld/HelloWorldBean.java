package com.irohal.springmicroservices.restful.helloworld;

import javax.swing.text.AsyncBoxView;

public class HelloWorldBean {

    private final String message;

    public HelloWorldBean(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

}

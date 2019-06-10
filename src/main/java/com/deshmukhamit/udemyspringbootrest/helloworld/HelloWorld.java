package com.deshmukhamit.udemyspringbootrest.helloworld;

public class HelloWorld {

    private String message;

    public HelloWorld(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorld [mesage=%s]", message);
    }
}

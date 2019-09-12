package com.jais.n_cube;

public class Message {
    private String message;


    Message(String message)
    {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
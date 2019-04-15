package com.spring.boot.rest.angularjs.ttsserver.dto;

public class MyResponse {

    private final String message;
    public MyResponse(String message) { this.message = message; }
    public String getMessage() { return message; }
}
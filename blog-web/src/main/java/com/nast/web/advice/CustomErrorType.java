package com.nast.web.advice;

public class CustomErrorType {

    private final  Integer httpStatusCode;

    private final String message;

    public CustomErrorType(Integer httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getMessage() {
        return message;
    }
}

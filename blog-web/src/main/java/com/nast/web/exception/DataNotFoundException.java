package com.nast.web.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends WebControllerException {

    public DataNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}

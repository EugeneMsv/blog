package com.nast.web.advice;

import com.nast.web.controllers.MainController;
import com.nast.web.exception.DataNotFoundException;
import com.nast.web.exception.WebControllerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackageClasses = MainController.class)
public class ControllersAdvice {

    @ExceptionHandler(WebControllerException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(WebControllerException ex) {
        return new ResponseEntity<>(new CustomErrorType(ex.getHttpStatus().value(), ex.getMessage()), ex.getHttpStatus());
    }
}

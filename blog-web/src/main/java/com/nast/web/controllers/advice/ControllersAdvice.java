package com.nast.web.controllers.advice;

import com.nast.web.controllers.MainController;
import com.nast.web.controllers.utils.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackageClasses = MainController.class)
public class ControllersAdvice {

    protected ResponseUtils responseUtils = ResponseUtils.getInstance();

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<String> handleControllerException(Exception ex) {
        return responseUtils.internalError(ex.getMessage());
    }
}

package com.nast.web.controllers;

import com.nast.domain.profiling.LogLevel;
import com.nast.domain.profiling.LoggableCall;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @LoggableCall(logLevel = LogLevel.DEBUG)
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("MainController.index");
        return "index";
    }
}

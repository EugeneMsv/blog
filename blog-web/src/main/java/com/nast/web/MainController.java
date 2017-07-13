package com.nast.web;

import com.nast.aspects.LoggableCall;
import com.nast.aspects.LogLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @LoggableCall(logLevel = LogLevel.ERROR, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("MainController.index");
        return "index";
    }
}

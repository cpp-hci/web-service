package edu.cpp.hci.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String healthcheck(){
        return "Working!!";
    }
}

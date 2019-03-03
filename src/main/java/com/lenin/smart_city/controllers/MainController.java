package com.lenin.smart_city.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MainController
 */
@Controller
@RequestMapping(path="/")
public class MainController {

    @GetMapping(path="")
    public String index(Principal principal) {
        return "welcome";
    }

    @GetMapping(path="error")
    public String error(Principal principal) {
        return "error/404";
    }
    
}
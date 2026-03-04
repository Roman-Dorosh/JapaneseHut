package org.roman.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String mainPageController() {
        return "mainPage/pgMain";
    }
}
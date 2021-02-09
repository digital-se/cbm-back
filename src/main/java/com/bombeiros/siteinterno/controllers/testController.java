package com.bombeiros.siteinterno.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class testController {
    
    @GetMapping("/test/{name}")
    public String test(@PathVariable String name) {
        return name;

    }
}

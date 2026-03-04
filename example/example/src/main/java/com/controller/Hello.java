package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Hello {
    
    @GetMapping("/hello")
    public String sayHelloGet(@RequestParam String name) {
        // print the name to console
        System.out.println(name);
        return name;
    }

    @PostMapping("/hello")
    public String sayHelloPost(@RequestBody String name) {
        // return the name received in the request body
        return name;
    }
}

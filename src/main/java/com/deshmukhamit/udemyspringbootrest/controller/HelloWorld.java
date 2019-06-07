package com.deshmukhamit.udemyspringbootrest.controller;

import com.deshmukhamit.udemyspringbootrest.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloWorld {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world/{name}")
    public HelloWorldBean helloWorldPathVariableBean(@PathVariable String name) {
        //throw new RuntimeException(String.format("Something went wrong %s", name));
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}

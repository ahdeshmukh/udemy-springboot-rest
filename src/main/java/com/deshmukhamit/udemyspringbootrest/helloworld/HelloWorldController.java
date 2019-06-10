package com.deshmukhamit.udemyspringbootrest.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Hello World");
    }

    @GetMapping("/hello-world/{name}")
    public HelloWorld helloWorldPathVariableBean(@PathVariable String name) {
        //throw new RuntimeException(String.format("Something went wrong %s", name));
        return new HelloWorld(String.format("Hello World, %s", name));
    }
}

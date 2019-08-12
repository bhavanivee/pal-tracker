package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    String msg = "hello";

    public WelcomeController(@Value("${welcome.message}") String param){
        this.msg = param;
    }

    @GetMapping("/")
    public String sayHello() {
        return msg;
    }
}
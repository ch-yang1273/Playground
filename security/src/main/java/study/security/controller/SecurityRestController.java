package study.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityRestController {

    @PostMapping("/test")
    public String login() {
        System.out.println("SecurityRestController.login");
        return "test";
    }
}

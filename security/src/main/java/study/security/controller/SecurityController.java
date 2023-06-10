package study.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String index() {
        System.out.println("SecurityController.index");
        return "home";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        System.out.println("SecurityController.loginForm");
        return "login-form";
    }
}

package com.study.cors.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CorsController {

    @GetMapping("/api")
    public String index() {
        log.info("cors test");
        return "cors test";
    }
}

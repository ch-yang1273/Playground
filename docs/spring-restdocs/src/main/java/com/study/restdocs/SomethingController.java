package com.study.restdocs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomethingController {

    @GetMapping("/")
    public ResponseEntity<SomethingResponse> getSomething(@RequestParam String param) {
        return ResponseEntity.ok().body(new SomethingResponse(10L, param));
    }
}

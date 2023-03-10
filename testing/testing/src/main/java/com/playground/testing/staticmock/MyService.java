package com.playground.testing.staticmock;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public class MyService {

    private final MyLocalDateTime myLocalDateTime;

    public MyService(MyLocalDateTime myLocalDateTime) {
        this.myLocalDateTime = myLocalDateTime;
    }

    public String doFunction() {
        LocalDateTime now = myLocalDateTime.now();
        return now.toString();
    }
}

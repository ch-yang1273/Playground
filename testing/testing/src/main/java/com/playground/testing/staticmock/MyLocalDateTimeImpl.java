package com.playground.testing.staticmock;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class MyLocalDateTimeImpl implements MyLocalDateTime {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}

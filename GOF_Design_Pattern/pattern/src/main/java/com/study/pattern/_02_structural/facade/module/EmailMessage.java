package com.study.pattern._02_structural.facade.module;

import lombok.Getter;

@Getter
public class EmailMessage {

    private String email;
    private String message;

    public EmailMessage(String email, String message) {
        this.email = email;
        this.message = message;
    }
}

package com.study.pattern.facade.module;

import lombok.Getter;

@Getter
public class EmailSettings {

    private String mailServer;
    private String username;
    private String password;

    public EmailSettings(String mailServer, String username, String password) {
        this.mailServer = mailServer;
        this.username = username;
        this.password = password;
    }
}

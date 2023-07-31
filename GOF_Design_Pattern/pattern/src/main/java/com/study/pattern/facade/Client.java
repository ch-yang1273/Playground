package com.study.pattern.facade;

import com.study.pattern.facade.module.EmailMessage;
import com.study.pattern.facade.module.EmailServer;
import com.study.pattern.facade.module.EmailSettings;

public class Client {

    public static void main(String[] args) {
        EmailSettings setting = new EmailSettings("mail.example.com", "user", "password");
        EmailServer server = new EmailServer();
        EmailMessage message = new EmailMessage("test@example.com", "Hello, World!");

        server.connect(setting);
        server.authenticate(setting);
        server.send(message);
        server.disconnect();
    }
}

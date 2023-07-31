package com.study.pattern.facade;

import com.study.pattern.facade.module.EmailMessage;
import com.study.pattern.facade.module.EmailSettings;

public class ClientUsingFacade {

    public static void main(String[] args) {
        EmailSettings setting = new EmailSettings("mail.example.com", "user", "password");
        EmailFacade facade = new EmailFacade(setting);
        EmailMessage message = new EmailMessage("test@example.com", "Hello, World!");

        facade.sendEmail(message);
    }
}

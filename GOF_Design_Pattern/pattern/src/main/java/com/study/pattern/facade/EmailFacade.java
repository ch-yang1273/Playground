package com.study.pattern.facade;

import com.study.pattern.facade.module.EmailMessage;
import com.study.pattern.facade.module.EmailServer;
import com.study.pattern.facade.module.EmailSettings;

public class EmailFacade {
    private EmailServer server;
    private EmailSettings setting;

    public EmailFacade(EmailSettings setting) {
        this.server = new EmailServer();
        this.setting = setting;
    }

    public void sendEmail(EmailMessage message) {
        server.connect(setting);
        server.authenticate(setting);
        server.send(message);
        server.disconnect();
    }
}

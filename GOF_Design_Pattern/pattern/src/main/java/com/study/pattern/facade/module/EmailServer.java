package com.study.pattern.facade.module;

public class EmailServer {

    public void connect(EmailSettings setting) {
        // Connect to the email server
    }

    public void authenticate(EmailSettings setting) {
        // Authenticate the user
    }

    public void send(EmailMessage message) {
        // Send the email
    }

    public void disconnect() {
        // Disconnect from the email server
    }
}

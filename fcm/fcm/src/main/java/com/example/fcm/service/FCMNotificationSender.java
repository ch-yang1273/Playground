package com.example.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FCMNotificationSender {

    public void sendNotification(String deviceToken, String title, String body) {
        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .setToken(deviceToken)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            // db에 알림 내역 저장
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

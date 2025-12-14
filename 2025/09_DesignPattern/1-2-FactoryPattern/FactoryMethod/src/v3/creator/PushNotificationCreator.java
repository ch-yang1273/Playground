package v3.creator;

import v3.notification.Notification;
import v3.notification.PushNotification;

/**
 * 푸시 알림 생성자 (ConcreteCreator)
 */
public class PushNotificationCreator extends NotificationCreator {

    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}
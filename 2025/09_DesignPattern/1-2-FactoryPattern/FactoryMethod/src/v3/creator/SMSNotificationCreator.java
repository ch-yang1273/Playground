package v3.creator;

import v3.notification.Notification;
import v3.notification.SMSNotification;

/**
 * SMS 알림 생성자 (ConcreteCreator)
 */
public class SMSNotificationCreator extends NotificationCreator {

    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}
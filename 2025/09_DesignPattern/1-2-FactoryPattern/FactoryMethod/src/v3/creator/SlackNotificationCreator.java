package v3.creator;

import v3.notification.Notification;
import v3.notification.SlackNotification;

/**
 * 슬랙 알림 생성자 (ConcreteCreator)
 */
public class SlackNotificationCreator extends NotificationCreator {

    @Override
    public Notification createNotification() {
        return new SlackNotification();
    }
}
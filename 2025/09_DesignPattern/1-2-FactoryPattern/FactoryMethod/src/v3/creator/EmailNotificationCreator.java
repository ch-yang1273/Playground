package v3.creator;

import v3.notification.EmailNotification;
import v3.notification.Notification;

/**
 * 이메일 알림 생성자 (ConcreteCreator)
 *
 * NotificationCreator의 팩토리 메서드를 구현하여
 * EmailNotification 객체를 생성합니다.
 */
public class EmailNotificationCreator extends NotificationCreator {

    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
package v3.notification;

/**
 * 이메일 알림 (ConcreteProduct)
 *
 * Notification 인터페이스의 구체적인 구현체입니다.
 * EmailNotificationCreator에 의해 생성됩니다.
 */
public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("=== 이메일 발송 ===");
        System.out.println("SMTP 서버에 연결 중...");
        System.out.println("이메일 내용: " + message);
        System.out.println("이메일 발송 완료!");
        System.out.println();
    }

    @Override
    public String getChannelType() {
        return "Email";
    }
}
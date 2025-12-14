package v3.notification;

/**
 * SMS 알림 (ConcreteProduct)
 */
public class SMSNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("=== SMS 발송 ===");
        System.out.println("통신사 API 호출 중...");
        System.out.println("SMS 내용: " + message);
        System.out.println("SMS 발송 완료!");
        System.out.println();
    }

    @Override
    public String getChannelType() {
        return "SMS";
    }
}
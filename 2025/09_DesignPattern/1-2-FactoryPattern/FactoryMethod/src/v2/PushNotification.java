package v2;

/**
 * 푸시 알림 구현체 (ConcreteProduct)
 */
public class PushNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("=== 푸시 알림 발송 ===");
        System.out.println("FCM/APNs 서버에 연결 중...");
        System.out.println("푸시 내용: " + message);
        System.out.println("푸시 알림 발송 완료!");
        System.out.println();
    }

    @Override
    public String getChannelType() {
        return "Push";
    }
}
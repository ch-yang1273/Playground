package v3.notification;

/**
 * 슬랙 알림 (ConcreteProduct)
 */
public class SlackNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("=== 슬랙 메시지 발송 ===");
        System.out.println("Slack Webhook 호출 중...");
        System.out.println("슬랙 내용: " + message);
        System.out.println("슬랙 메시지 발송 완료!");
        System.out.println();
    }

    @Override
    public String getChannelType() {
        return "Slack";
    }
}
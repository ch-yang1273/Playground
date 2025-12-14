package v2;

/**
 * 이메일 알림 구현체 (ConcreteProduct)
 *
 * [v1 대비 개선점]
 * - 이메일 발송 로직이 별도 클래스로 분리됨
 * - 이메일 관련 변경이 다른 알림 채널에 영향을 주지 않음
 * - 단위 테스트가 용이해짐
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
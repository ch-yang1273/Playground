package v2;

/**
 * v2 데모: Simple Factory 패턴
 *
 * v1의 문제점을 개선했지만, 여전히 한계점이 존재합니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  v2: Simple Factory 패턴");
        System.out.println("========================================");
        System.out.println();

        // 팩토리를 통해 알림 객체 생성
        // 클라이언트는 구체적인 클래스(EmailNotification 등)를 직접 생성하지 않음
        Notification email = NotificationFactory.createNotification("email");
        Notification sms = NotificationFactory.createNotification("sms");
        Notification push = NotificationFactory.createNotification("push");
        Notification slack = NotificationFactory.createNotification("slack");

        // 다형성을 활용한 알림 발송
        email.send("회원가입을 환영합니다!");
        sms.send("인증번호: 123456");
        push.send("새로운 메시지가 도착했습니다.");
        slack.send("배포가 완료되었습니다.");

        // 지원하지 않는 타입 - 예외 발생
        try {
            Notification kakaotalk = NotificationFactory.createNotification("kakaotalk");
        } catch (IllegalArgumentException e) {
            System.out.println("[오류] " + e.getMessage());
            System.out.println();
        }

        System.out.println("========================================");
        System.out.println("  v1 대비 개선점");
        System.out.println("========================================");
        System.out.println("1. 객체 생성 로직이 Factory로 분리됨");
        System.out.println("2. 각 알림 타입이 별도 클래스로 분리됨 (SRP 개선)");
        System.out.println("3. 클라이언트가 구체 클래스에 의존하지 않음");
        System.out.println();

        System.out.println("========================================");
        System.out.println("  v2의 한계점");
        System.out.println("========================================");
        System.out.println("1. NotificationFactory 내부에 여전히 if-else 존재");
        System.out.println("2. 새 채널 추가 시 Factory 클래스 수정 필요 (OCP 위반)");
        System.out.println();
        System.out.println("-> v3에서 Factory Method 패턴으로 해결합니다.");
    }
}
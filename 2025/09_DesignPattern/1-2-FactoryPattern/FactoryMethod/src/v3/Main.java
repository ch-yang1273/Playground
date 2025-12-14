package v3;

import v3.creator.*;

/**
 * v3 데모: 팩토리 메서드 패턴 (Factory Method Pattern)
 *
 * GoF 디자인 패턴의 최종 형태입니다.
 * OCP(개방-폐쇄 원칙)를 완전히 준수합니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  v3: 팩토리 메서드 패턴");
        System.out.println("========================================");
        System.out.println();

        // 각 Creator를 통해 알림 발송
        // 클라이언트는 구체적인 Notification 클래스를 전혀 알 필요가 없음!
        NotificationCreator emailCreator = new EmailNotificationCreator();
        NotificationCreator smsCreator = new SMSNotificationCreator();
        NotificationCreator pushCreator = new PushNotificationCreator();
        NotificationCreator slackCreator = new SlackNotificationCreator();

        // Creator의 템플릿 메서드를 통해 알림 발송
        emailCreator.sendNotification("회원가입을 환영합니다!");
        smsCreator.sendNotification("인증번호: 123456");
        pushCreator.sendNotification("새로운 메시지가 도착했습니다.");
        slackCreator.sendNotification("배포가 완료되었습니다.");

        System.out.println("========================================");
        System.out.println("  v2 대비 개선점 (최종)");
        System.out.println("========================================");
        System.out.println("1. if-else가 완전히 제거됨");
        System.out.println("2. 새 채널 추가 시 기존 코드 수정 불필요 (OCP 준수)");
        System.out.println("3. 객체 생성을 서브클래스에 위임 (DIP 준수)");
        System.out.println();

        System.out.println("========================================");
        System.out.println("  새 채널 추가 방법 (예: 카카오톡)");
        System.out.println("========================================");
        System.out.println("1. KakaotalkNotification 클래스 생성 (Notification 구현)");
        System.out.println("2. KakaotalkNotificationCreator 클래스 생성 (Creator 상속)");
        System.out.println("3. 기존 코드는 전혀 수정하지 않음!");
        System.out.println();

        // 다형성 활용 예시
        System.out.println("========================================");
        System.out.println("  다형성 활용 예시");
        System.out.println("========================================");
        NotificationCreator[] creators = {
            new EmailNotificationCreator(),
            new SMSNotificationCreator(),
            new PushNotificationCreator(),
            new SlackNotificationCreator()
        };

        for (NotificationCreator creator : creators) {
            creator.sendNotification("동일한 메시지를 여러 채널로 발송!");
        }
    }
}
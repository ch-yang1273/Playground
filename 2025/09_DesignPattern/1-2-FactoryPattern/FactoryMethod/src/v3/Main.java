package v3;

import v3.creator.*;

/**
 * v3 데모: 팩토리 메서드 패턴 (Factory Method Pattern)
 *
 * 이 데모에서 Creator가 필요한 이유를 명확히 보여줍니다:
 * 1. 공통 로직 (로깅, 검증, 기록) - 템플릿 메서드
 * 2. 의존성 주입 - NotificationService
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("  v3: 팩토리 메서드 패턴 - Creator가 필요한 이유");
        System.out.println("================================================================");
        System.out.println();

        // ============================================================
        // 1. Creator의 공통 로직 (템플릿 메서드) 데모
        // ============================================================
        System.out.println("[ 데모 1: Creator의 공통 로직 ]");
        System.out.println("Creator가 로깅, 검증, 기록 저장을 자동으로 처리합니다.");
        System.out.println("이 로직이 없다면 4개의 Notification에 각각 작성해야 합니다!");
        System.out.println("------------------------------------------------------------");

        NotificationCreator emailCreator = new EmailNotificationCreator();
        emailCreator.sendNotification("회원가입을 환영합니다!");

        // ============================================================
        // 2. 유효성 검사 데모
        // ============================================================
        System.out.println("[ 데모 2: 공통 유효성 검사 ]");
        System.out.println("빈 메시지를 발송하면 Creator가 자동으로 검증합니다.");
        System.out.println("------------------------------------------------------------");

        try {
            emailCreator.sendNotification("");  // 빈 메시지 - 예외 발생!
        } catch (IllegalArgumentException e) {
            System.out.println("[예외 발생] " + e.getMessage());
            System.out.println();
        }

        // ============================================================
        // 3. 의존성 주입 데모 (NotificationService)
        // ============================================================
        System.out.println("[ 데모 3: 의존성 주입 - Creator 교체만으로 채널 변경 ]");
        System.out.println("NotificationService는 구체적인 Notification을 모릅니다!");
        System.out.println("Creator만 교체하면 알림 채널이 변경됩니다.");
        System.out.println("------------------------------------------------------------");

        // 이메일로 알림 발송
        NotificationService service = new NotificationService(new EmailNotificationCreator());
        service.notifyUser("user123", "주문이 완료되었습니다.");

        // Creator만 교체하면 슬랙으로 변경! (코드 수정 없음)
        service.setCreator(new SlackNotificationCreator());
        service.notifyUser("user123", "주문이 완료되었습니다.");

        // ============================================================
        // 4. 발송 기록 조회
        // ============================================================
        System.out.println("[ 데모 4: 발송 기록 조회 ]");
        System.out.println("Creator가 자동으로 저장한 발송 기록입니다.");
        System.out.println("------------------------------------------------------------");

        System.out.println("=== 발송 기록 ===");
        for (String record : NotificationCreator.getHistory()) {
            System.out.println("  " + record);
        }
        System.out.println();

        // ============================================================
        // 요약
        // ============================================================
        System.out.println("================================================================");
        System.out.println("  Creator가 필요한 이유 요약");
        System.out.println("================================================================");
        System.out.println("1. 공통 로직 재사용: 로깅, 검증, 기록 저장을 한 곳에서 관리");
        System.out.println("2. 의존성 주입: Creator만 교체하면 알림 채널 변경 가능");
        System.out.println("3. OCP 준수: 새 채널 추가 시 기존 코드 수정 불필요");
        System.out.println();
        System.out.println("만약 Creator가 없다면?");
        System.out.println("-> 4개의 Notification에 로깅, 검증, 기록 로직을 중복 작성해야 함!");
    }
}
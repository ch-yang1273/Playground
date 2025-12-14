package v1;

/**
 * v1 데모: if-else로 분기 처리하는 알림 시스템
 *
 * 이 버전의 문제점을 확인한 후, v2에서 개선합니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  v1: if-else 분기 처리 방식");
        System.out.println("========================================");
        System.out.println();

        NotificationService service = new NotificationService();

        // 다양한 채널로 알림 발송
        service.sendNotification("email", "회원가입을 환영합니다!");
        service.sendNotification("sms", "인증번호: 123456");
        service.sendNotification("push", "새로운 메시지가 도착했습니다.");
        service.sendNotification("slack", "배포가 완료되었습니다.");

        // 지원하지 않는 타입
        service.sendNotification("kakaotalk", "테스트 메시지");

        System.out.println("========================================");
        System.out.println("  v1의 문제점");
        System.out.println("========================================");
        System.out.println("1. 새 채널 추가 시 NotificationService 수정 필요 (OCP 위반)");
        System.out.println("2. 모든 채널 로직이 한 클래스에 집중 (SRP 위반)");
        System.out.println("3. 타입을 문자열로 전달하여 타입 안전성 부족");
        System.out.println();
        System.out.println("-> v2에서 Simple Factory 패턴으로 개선합니다.");
    }
}

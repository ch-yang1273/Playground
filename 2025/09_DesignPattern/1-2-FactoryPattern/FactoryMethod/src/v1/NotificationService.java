package v1;

/**
 * v1: if-else로 분기 처리하는 알림 서비스
 *
 * [문제점]
 * 1. OCP(개방-폐쇄 원칙) 위반
 *    - 새로운 알림 채널(예: 카카오톡)을 추가하려면 이 클래스를 수정해야 함
 *    - "확장에는 열려있고, 수정에는 닫혀있어야 한다"는 원칙 위반
 *
 * 2. SRP(단일 책임 원칙) 위반
 *    - 하나의 클래스가 모든 알림 채널의 발송 로직을 담당
 *    - 이메일, SMS, 푸시, 슬랙 각각의 변경 이유가 이 클래스를 수정하게 만듦
 *
 * 3. 유지보수 어려움
 *    - 코드가 길어지고 복잡해짐
 *    - 특정 채널의 로직 변경 시 다른 채널에 영향을 줄 위험
 *
 * 4. 테스트 어려움
 *    - 특정 알림 채널만 단위 테스트하기 어려움
 *    - 모든 분기를 테스트해야 함
 */
public class NotificationService {

    /**
     * 알림을 발송하는 메서드
     *
     * @param type    알림 타입 ("email", "sms", "push", "slack")
     * @param message 발송할 메시지
     */
    public void sendNotification(String type, String message) {
        // [문제점] 새로운 알림 타입을 추가할 때마다 이 메서드를 수정해야 함
        if (type.equals("email")) {
            sendEmail(message);
        } else if (type.equals("sms")) {
            sendSMS(message);
        } else if (type.equals("push")) {
            sendPush(message);
        } else if (type.equals("slack")) {
            sendSlack(message);
        }
        // [문제점] 새 채널 추가 시 여기에 else if를 계속 추가해야 함
        // else if (type.equals("kakaotalk")) {
        //     sendKakaotalk(message);
        // }
        else {
            System.out.println("[오류] 지원하지 않는 알림 타입: " + type);
        }
    }

    // [문제점] 모든 채널의 발송 로직이 한 클래스에 모여있음
    // 각 메서드가 복잡해지면 이 클래스가 거대해짐

    private void sendEmail(String message) {
        System.out.println("=== 이메일 발송 ===");
        System.out.println("SMTP 서버에 연결 중...");
        System.out.println("이메일 내용: " + message);
        System.out.println("이메일 발송 완료!");
        System.out.println();
    }

    private void sendSMS(String message) {
        System.out.println("=== SMS 발송 ===");
        System.out.println("통신사 API 호출 중...");
        System.out.println("SMS 내용: " + message);
        System.out.println("SMS 발송 완료!");
        System.out.println();
    }

    private void sendPush(String message) {
        System.out.println("=== 푸시 알림 발송 ===");
        System.out.println("FCM/APNs 서버에 연결 중...");
        System.out.println("푸시 내용: " + message);
        System.out.println("푸시 알림 발송 완료!");
        System.out.println();
    }

    private void sendSlack(String message) {
        System.out.println("=== 슬랙 메시지 발송 ===");
        System.out.println("Slack Webhook 호출 중...");
        System.out.println("슬랙 내용: " + message);
        System.out.println("슬랙 메시지 발송 완료!");
        System.out.println();
    }
}

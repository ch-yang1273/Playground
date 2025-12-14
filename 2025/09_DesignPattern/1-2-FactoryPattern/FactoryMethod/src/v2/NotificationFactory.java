package v2;

/**
 * 알림 팩토리 (Simple Factory)
 *
 * [v1 대비 개선점]
 * - 객체 생성 로직이 클라이언트 코드에서 분리됨
 * - 클라이언트는 구체적인 클래스를 몰라도 됨
 * - 생성 로직이 한 곳에 집중되어 관리가 용이함
 *
 * [한계점 - v3에서 해결]
 * - 여전히 if-else 분기가 존재 (OCP 위반)
 * - 새로운 알림 타입 추가 시 이 팩토리 클래스를 수정해야 함
 * - 엄밀히 말해 "Simple Factory"는 GoF 디자인 패턴이 아님 (프로그래밍 관용구)
 */
public class NotificationFactory {

    /**
     * 알림 객체를 생성하는 정적 팩토리 메서드
     *
     * @param type 알림 타입
     * @return 해당 타입의 Notification 객체
     * @throws IllegalArgumentException 지원하지 않는 타입인 경우
     */
    public static Notification createNotification(String type) {
        // [한계점] 새로운 타입 추가 시 이 메서드를 수정해야 함
        if (type.equals("email")) {
            return new EmailNotification();
        } else if (type.equals("sms")) {
            return new SMSNotification();
        } else if (type.equals("push")) {
            return new PushNotification();
        } else if (type.equals("slack")) {
            return new SlackNotification();
        }
        // [한계점] 새 채널 추가 시 여기에 else if를 계속 추가해야 함
        // else if (type.equals("kakaotalk")) {
        //     return new KakaotalkNotification();
        // }
        else {
            throw new IllegalArgumentException("지원하지 않는 알림 타입: " + type);
        }
    }
}
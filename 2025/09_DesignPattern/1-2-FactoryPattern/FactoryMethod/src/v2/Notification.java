package v2;

/**
 * 알림 인터페이스 (Product)
 *
 * [v1 대비 개선점]
 * - 알림의 공통 동작을 인터페이스로 추상화
 * - 클라이언트는 구체적인 알림 타입을 몰라도 됨 (다형성)
 * - 새로운 알림 타입 추가 시 이 인터페이스만 구현하면 됨
 */
public interface Notification {

    /**
     * 알림을 발송한다.
     *
     * @param message 발송할 메시지
     */
    void send(String message);

    /**
     * 알림 채널 타입을 반환한다.
     *
     * @return 채널 타입 (예: "Email", "SMS" 등)
     */
    String getChannelType();
}
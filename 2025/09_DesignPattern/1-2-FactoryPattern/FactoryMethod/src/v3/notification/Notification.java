package v3.notification;

/**
 * 알림 인터페이스 (Product)
 *
 * 팩토리 메서드 패턴에서 "제품(Product)" 역할을 담당합니다.
 * Creator가 생성하는 객체의 공통 인터페이스를 정의합니다.
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
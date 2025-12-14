package v3.creator;

import v3.notification.Notification;

/**
 * 알림 생성자 추상 클래스 (Creator)
 *
 * 팩토리 메서드 패턴의 핵심!
 * - createNotification(): 팩토리 메서드 (서브클래스에서 구현)
 * - sendNotification(): 템플릿 메서드 (공통 로직)
 *
 * [v2 대비 개선점]
 * - if-else가 완전히 제거됨
 * - 새로운 알림 채널 추가 시 기존 코드 수정 없이 확장 가능 (OCP 준수)
 * - 객체 생성을 서브클래스에 위임 (DIP 준수)
 *
 * [패턴의 핵심 원리]
 * Creator는 어떤 ConcreteProduct가 생성될지 모릅니다.
 * 단지 Notification 인터페이스만 알고 있으며,
 * 실제 객체 생성은 서브클래스(ConcreteCreator)가 결정합니다.
 */
public abstract class NotificationCreator {

    /**
     * 팩토리 메서드 (Factory Method)
     *
     * 서브클래스에서 구현하여 구체적인 Notification 객체를 생성합니다.
     * 이 메서드가 "팩토리 메서드 패턴"의 이름이 된 핵심 메서드입니다.
     *
     * @return Notification 객체
     */
    public abstract Notification createNotification();

    /**
     * 템플릿 메서드 (Template Method)
     *
     * 알림 발송의 공통 로직을 정의합니다.
     * 팩토리 메서드를 호출하여 객체를 생성하고 사용합니다.
     *
     * @param message 발송할 메시지
     */
    public void sendNotification(String message) {
        // 팩토리 메서드를 호출하여 알림 객체 생성
        Notification notification = createNotification();

        // 생성된 객체를 사용하여 알림 발송
        System.out.println("[" + notification.getChannelType() + " Creator] 알림 발송 시작");
        notification.send(message);
    }
}
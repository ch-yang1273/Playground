package v3;

import v3.creator.NotificationCreator;

/**
 * 알림 서비스 (Client)
 *
 * [Creator가 필요한 또 다른 이유: 의존성 주입]
 *
 * 이 클래스는 NotificationCreator만 알면 됩니다.
 * 구체적인 EmailNotification, SMSNotification 등을 전혀 모릅니다!
 *
 * 장점:
 * 1. Creator만 교체하면 알림 채널이 변경됨 (코드 수정 없음)
 * 2. 테스트 시 MockCreator를 주입할 수 있음
 * 3. 런타임에 동적으로 채널 변경 가능
 */
public class NotificationService {

    private NotificationCreator creator;

    /**
     * 생성자 주입 (Constructor Injection)
     *
     * 어떤 Creator가 주입되느냐에 따라 다른 알림 채널로 발송됨
     * - EmailNotificationCreator 주입 → 이메일 발송
     * - SlackNotificationCreator 주입 → 슬랙 발송
     */
    public NotificationService(NotificationCreator creator) {
        this.creator = creator;
    }

    /**
     * Creator 변경 (Setter Injection)
     *
     * 런타임에 동적으로 알림 채널 변경 가능
     */
    public void setCreator(NotificationCreator creator) {
        this.creator = creator;
    }

    /**
     * 사용자에게 알림 발송
     */
    public void notifyUser(String userId, String message) {
        String formattedMessage = "[To: " + userId + "] " + message;
        creator.sendNotification(formattedMessage);
    }

    /**
     * 여러 사용자에게 알림 발송
     */
    public void notifyUsers(String[] userIds, String message) {
        for (String userId : userIds) {
            notifyUser(userId, message);
        }
    }

    /**
     * 긴급 알림 발송 (메시지 앞에 [긴급] 추가)
     */
    public void notifyUrgent(String userId, String message) {
        notifyUser(userId, "[긴급] " + message);
    }
}
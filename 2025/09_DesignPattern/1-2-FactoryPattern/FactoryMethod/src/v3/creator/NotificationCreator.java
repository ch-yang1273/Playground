package v3.creator;

import v3.notification.Notification;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 알림 생성자 추상 클래스 (Creator)
 *
 * 팩토리 메서드 패턴의 핵심!
 * - createNotification(): 팩토리 메서드 (서브클래스에서 구현)
 * - sendNotification(): 템플릿 메서드 (공통 로직)
 *
 * [Creator가 필요한 이유]
 * 이 클래스가 없으면 아래 공통 로직을 각 Notification에 중복 작성해야 함!
 * - 발송 전 로깅
 * - 메시지 유효성 검사
 * - 발송 후 기록 저장
 *
 * Creator를 통해 이 공통 로직을 한 곳에서 관리할 수 있음
 */
public abstract class NotificationCreator {

    // 발송 기록 저장소 (실제로는 DB나 파일에 저장)
    private static final List<String> history = new ArrayList<>();

    /**
     * 팩토리 메서드 (Factory Method)
     *
     * 서브클래스에서 구현하여 구체적인 Notification 객체를 생성합니다.
     *
     * @return Notification 객체
     */
    public abstract Notification createNotification();

    /**
     * 템플릿 메서드 (Template Method)
     *
     * [핵심] 이 메서드가 Creator가 필요한 이유!
     * 알림 발송의 공통 로직을 정의합니다.
     * 만약 Creator가 없다면, 이 로직을 4개의 Notification 클래스에 각각 작성해야 함
     *
     * @param message 발송할 메시지
     */
    public void sendNotification(String message) {
        // 1. 발송 전 로깅 (공통 로직)
        logBefore(message);

        // 2. 유효성 검사 (공통 로직)
        validate(message);

        // 3. 팩토리 메서드로 객체 생성 (서브클래스가 결정)
        Notification notification = createNotification();

        // 4. 알림 발송
        notification.send(message);

        // 5. 발송 기록 저장 (공통 로직)
        saveHistory(notification.getChannelType(), message);
    }

    /**
     * 발송 전 로깅 (공통 로직)
     */
    private void logBefore(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[LOG " + timestamp + "] 알림 발송 준비 중...");
    }

    /**
     * 메시지 유효성 검사 (공통 로직)
     */
    private void validate(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("메시지가 비어있습니다!");
        }
        if (message.length() > 1000) {
            throw new IllegalArgumentException("메시지가 너무 깁니다! (최대 1000자)");
        }
        System.out.println("[검증] 메시지 유효성 검사 통과");
    }

    /**
     * 발송 기록 저장 (공통 로직)
     */
    private void saveHistory(String channelType, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String record = String.format("[%s] %s: %s",
                timestamp, channelType, message.substring(0, Math.min(message.length(), 20)) + "...");
        history.add(record);
        System.out.println("[기록] 발송 내역 저장 완료");
        System.out.println();
    }

    /**
     * 발송 기록 조회
     */
    public static List<String> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * 발송 기록 초기화
     */
    public static void clearHistory() {
        history.clear();
    }
}
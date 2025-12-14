# 팩토리 메서드 패턴 (Factory Method Pattern)

알림 시스템 예제를 통해 팩토리 메서드 패턴을 점진적으로 학습합니다.

## 버전별 진화 과정

| 버전 | 패턴 | OCP | 설명 |
|------|------|-----|------|
| v1 | if-else 분기 | X | 문제점 인식 |
| v2 | Simple Factory | X | 생성 로직 분리 (한계점 존재) |
| v3 | Factory Method | O | 최종 패턴 |

---

## 언제 어떤 패턴을 사용해야 하는가?

### v1: if-else 분기 - 사용하지 마세요
```java
if (type.equals("email")) { sendEmail(); }
else if (type.equals("sms")) { sendSMS(); }
```

**문제점**
- 새 타입 추가 시 기존 코드 수정 필요 (OCP 위반)
- 모든 로직이 한 클래스에 집중 (SRP 위반)
- 타입을 문자열로 전달하여 타입 안전성 부족

---

### v2: Simple Factory - 간단한 경우에 적합

```java
Notification n = NotificationFactory.create("email");
n.send("메시지");
```

**적합한 경우**
- 객체 종류가 적고 거의 변하지 않을 때
- 빠르게 구현해야 할 때
- 생성 전후 공통 로직이 필요 없을 때

**한계점**
- Factory 내부에 여전히 if-else 존재
- 새 타입 추가 시 Factory 수정 필요 (OCP 위반)

---

### v3: Factory Method - 확장성이 필요할 때 사용

```java
NotificationCreator creator = new EmailNotificationCreator();
creator.sendNotification("메시지");
```

**적합한 경우**

| 상황 | 설명 |
|------|------|
| **공통 로직 필요** | 생성 전후 로깅, 검증, 기록 저장 등 |
| **확장 가능성** | 새로운 타입이 자주 추가될 예정 |
| **의존성 주입** | Creator를 주입받아 런타임에 교체 |
| **프레임워크 설계** | 사용자가 확장할 수 있는 포인트 제공 |

**v3에서 Creator가 필요한 이유**

```java
// Creator의 템플릿 메서드 - 공통 로직 재사용
public void sendNotification(String message) {
    logBefore(message);              // 1. 로깅
    validate(message);               // 2. 검증
    Notification n = createNotification();  // 3. 팩토리 메서드
    n.send(message);                 // 4. 발송
    saveHistory(...);                // 5. 기록 저장
}
```

Creator가 없다면 이 공통 로직을 각 Notification에 중복 작성해야 합니다!

**의존성 주입 예시**

```java
// Creator만 교체하면 알림 채널 변경!
NotificationService service = new NotificationService(creator);

service.setCreator(new EmailNotificationCreator());  // 이메일
service.setCreator(new SlackNotificationCreator());  // 슬랙으로 변경
```

---

## 패턴 선택 가이드

```
객체 생성이 필요한가?
    │
    ├─ 타입이 1-2개이고 변할 일 없음 → 직접 생성 (new)
    │
    ├─ 타입이 여러 개지만 거의 변하지 않음 → Simple Factory (v2)
    │
    └─ 아래 중 하나라도 해당하면 → Factory Method (v3)
        ├─ 새 타입이 자주 추가될 예정
        ├─ 생성 전후 공통 로직 필요
        ├─ Creator를 주입받아 교체해야 함
        └─ 프레임워크/라이브러리 설계
```

---

## 컴파일 및 실행

```bash
cd src

# v1 (if-else)
javac v1/*.java && java v1.Main

# v2 (Simple Factory)
javac v2/*.java && java v2.Main

# v3 (Factory Method)
javac v3/notification/*.java v3/creator/*.java v3/*.java && java v3.Main
```

---

## 프로젝트 구조

```
src/
├── v1/                              # if-else 분기
│   ├── NotificationService.java
│   └── Main.java
├── v2/                              # Simple Factory
│   ├── Notification.java            ← 인터페이스
│   ├── *Notification.java           ← 구현체들
│   ├── NotificationFactory.java     ← 팩토리 (if-else)
│   └── Main.java
└── v3/                              # Factory Method
    ├── notification/
    │   ├── Notification.java        ← Product 인터페이스
    │   └── *Notification.java       ← ConcreteProduct
    ├── creator/
    │   ├── NotificationCreator.java ← Creator 추상 클래스
    │   └── *NotificationCreator.java← ConcreteCreator
    ├── NotificationService.java     ← 의존성 주입 예시
    └── Main.java
```

---

## 핵심 학습 포인트

1. **OCP (개방-폐쇄 원칙)**: 확장에는 열려있고, 수정에는 닫혀있어야 한다
2. **템플릿 메서드**: Creator에서 공통 로직을 정의하고, 팩토리 메서드만 서브클래스가 구현
3. **의존성 역전**: 고수준 모듈(Service)이 저수준 모듈(Notification)에 직접 의존하지 않고, 추상화(Creator)에 의존
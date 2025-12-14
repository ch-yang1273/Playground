# 팩토리 메서드 패턴 (Factory Method Pattern)

알림 시스템 예제를 통해 팩토리 메서드 패턴을 점진적으로 학습합니다.

## 버전별 진화 과정

| 버전 | 패턴 | 설명 |
|------|------|------|
| v1 | if-else 분기 | 문제점 인식 (OCP 위반) |
| v2 | Simple Factory | 생성 로직 분리 (한계점 존재) |
| v3 | Factory Method | 최종 패턴 (OCP 준수) |

## 컴파일 및 실행

### 전체 컴파일
```bash
cd src
javac v1/*.java
javac v2/*.java
javac v3/**/*.java
```

### 버전별 실행

**v1 실행 (if-else 분기)**
```bash
cd src
javac v1/*.java
java v1.Main
```

**v2 실행 (Simple Factory)**
```bash
cd src
javac v2/*.java
java v2.Main
```

**v3 실행 (Factory Method)**
```bash
cd src
javac v3/notification/*.java v3/creator/*.java v3/Main.java
java v3.Main
```

## 프로젝트 구조

```
src/
├── v1/                          # if-else 분기 (문제점 인식)
│   ├── NotificationService.java
│   └── Main.java
├── v2/                          # Simple Factory
│   ├── Notification.java
│   ├── *Notification.java
│   ├── NotificationFactory.java
│   └── Main.java
└── v3/                          # Factory Method Pattern
    ├── notification/
    │   ├── Notification.java
    │   └── *Notification.java
    ├── creator/
    │   ├── NotificationCreator.java
    │   └── *NotificationCreator.java
    └── Main.java
```

## 학습 포인트

### v1의 문제점
- OCP(개방-폐쇄 원칙) 위반: 새 채널 추가 시 기존 코드 수정 필요
- SRP(단일 책임 원칙) 위반: 모든 로직이 한 클래스에 집중
- 타입 안전성 부족: 문자열로 타입 전달

### v2의 개선점과 한계
- 개선: 객체 생성 로직을 Factory로 분리
- 한계: Factory 내부에 여전히 if-else 존재

### v3의 장점
- OCP 준수: 새 채널 추가 시 기존 코드 수정 없이 확장 가능
- 객체 생성을 서브클래스에 위임
- DIP(의존성 역전 원칙) 준수
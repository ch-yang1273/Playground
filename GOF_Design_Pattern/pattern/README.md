# GOF Design Pattern

## 목차

### Creational Patterns
- [Singleton](#singleton)
- [Factory method](#factory-method)

### Structural Patterns
- [Facade](#facade)

### Behavioral Patterns

## Singleton

- 객체의 인스턴스가 오직 1개만 생성되는 패턴이다.
- 아래 예시와 같이 Thread-Safe하게 Singleton 클래스를 생성하기 위한 여러 방법이 있다.

### 1. synchronized

- getInstance()를 호출할 때 마다 synchronized가 적용되어 약간의 성능 저하가 발생한다.

```java
public class Settings {
    private static Settings instance;
    
    private Settings() {}
    
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        
        return instance;
    }
}
```

### 2. double checked locking

- getInstance()를 호출할 때 인스턴스가 없을 때만 synchronized가 적용된다.

```java
public class Settings {
    private static Settings instance = new Settings();
    
    private Settings() {}
    
    public static Settings getInstance() {        
        return instance;
    }
}
```

### 3. 이른 초기화 (Eager Initialization)

- 애플리케이션 로딩 시에 바로 생성된다.
- 사용을 하지 않더라도 로딩 시에 무조건 생성하는 것이 단점
- 단순한 방법이지만 간단해서 좋은 것 같다.
- Runtime 클래스가 이 방식을 사용한다.

```java
public class Runtime {
    private static final Runtime currentRuntime = new Runtime();

    //...
    
    public static Runtime getRuntime() {
        return currentRuntime;
    }
    
    //...
}
```

```java
public class Settings {
    private static Settings instance = new Settings();

    // 생성자의 접근 제한자를 private로 해서 호출하지 못하도록 함
    private Settings() {}

    public static Settings getInstance() {
        return instance;
    }
}
```

### 4. static inner 클래스 사용

- getInstance()를 최초로 호출할 때, INSTANCE가 초기화 된다.
- [static inner class 는 언제 로드가 될까? 로드와 초기화?](https://kdhyo98.tistory.com/70)
- [클래스의 로드 시점 - 인프런 | 질문 & 답변](https://www.inflearn.com/questions/491491/%ED%81%B4%EB%9E%98%EC%8A%A4%EC%9D%98-%EB%A1%9C%EB%93%9C-%EC%8B%9C%EC%A0%90)

```java
public class Settings {

    // 생성자의 접근 제한자를 private로 해서 호출하지 못하도록 함
    private Settings() {}

    // SettingsHolder는 getInstance()로 최초로 참조될 때 생성된다.
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    public static Settings getInstance() {
        return SettingsHolder.instance;
    }
}
```

## Factory method

- 객체 생성에 관련된 로직을 서브 클래스에 위임한다.
- 인스턴스를 생성하는 책임을 구체적인 클래스가 아닌, 추상적인 인터페이스의 메서드로 감싼다.

### ["팩토리 메서드" 적용 전](src/main/java/com/study/pattern/_01_creational/factory/_01_before)

- 요구 조건이 추가 될 때 마다, 기존 코드를 수정해야 한다.
- 조건을 따르는 생성 코드가 복잡하다.

### ["팩토리 메서드" 적용 후](src/main/java/com/study/pattern/_01_creational/factory/_02_after)

- Product와 Creator 간의 커플링이 느슨해졌다.
- OCP 원칙(개방-폐쇄 원칙: 확장에는 열려있고 변경에는 닫혀있는)을 지킬 수 있다.
- 역할에 따른 클래스가 많아지는 단점이 있다.

## Facade

- "퍼사드"라는 단어는 프랑스어로, 건물의 정면이나 외관을 의미한다.
- "퍼사드"는 복잡한 내부 시스템을 감추고 간단한 인터페이스를 제공함으로써, 코드를 단순화하는 디자인 패턴이다.

### ["퍼사드" 적용 전](src/main/java/com/study/pattern/_02_structural/facade/Client.java)

```java
public class Client {

    public void send() {
        EmailSettings setting = new EmailSettings("mail.example.com", "user", "password");
        EmailServer server = new EmailServer();
        EmailMessage message = new EmailMessage("test@example.com", "Hello, World!");

        server.connect(setting);
        server.authenticate(setting);
        server.send(message);
        server.disconnect();
    }
}
```

### ["퍼사드" 적용 후](src/main/java/com/study/pattern/_02_structural/facade/ClientUsingFacade.java)

```java
public class ClientUsingFacade {

    public void send() {
        EmailSettings setting = new EmailSettings("mail.example.com", "user", "password");
        EmailFacade facade = new EmailFacade(setting);
        EmailMessage message = new EmailMessage("test@example.com", "Hello, World!");

        facade.sendEmail(message);
    }
}
```

## 참고

- [백기선 - 코딩으로 학습하는 GoF의 디자인 패턴](https://inf.run/C4vW)
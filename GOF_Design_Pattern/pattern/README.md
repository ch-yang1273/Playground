# GOF Design Pattern

## Singleton Pattern

객체의 인스턴스가 오직 1개만 생성되는 패턴

```java
/*
 * 1. synchronized
 */
public class Settings {
    private static Settings instance;
    
    // 생성자의 접근 제한자를 private로 해서 호출하지 못하도록 함
    private Settings() {}
    
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        
        return instance;
    }
}

/*
 * 1. synchronized
 */
public class Settings {
    private static Settings instance = new Settings();
    
    // 생성자의 접근 제한자를 private로 해서 호출하지 못하도록 함
    private Settings() {}
    
    public static synchronized Settings getInstance() {        
        return instance;
    }
}
```

## Facade Pattern

- "퍼사드"라는 단어는 프랑스어로, 건물의 정면이나 외관을 의미한다.
- "퍼사드"는 복잡한 내부 시스템을 감추고 간단한 인터페이스를 제공함으로써, 코드를 단순화하는 디자인 패턴이다.
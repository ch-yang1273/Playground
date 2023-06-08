# JSON과 Enum 필드 상호 변환 : @JsonValue, @JsonCreator, @JsonFormat (+@JsonProperty)

이 프로젝트에서는 스프링의 Rest Controller에서 사용하는 DTO 클래스의 Enum 필드가 JSON으로 어떻게 변환되는지, 그리고 JSON이 어떻게 다시 Enum 필드로 변환되는지에 대해 알아보았습니다.

[EnumController](https://github.com/ch-yang1273/Playground/blob/master/enumjson/src/main/java/study/enumjson/EnumController.java)

## 1. Default

[MyEnumV1.java](https://github.com/ch-yang1273/Playground/blob/master/enumjson/src/main/java/study/enumjson/MyEnumV1.java)

```java
public enum MyEnumV1 {
    VALUE("value"),
    UNDER_SCORE("underscore")
    ;

    private final String value;

    MyEnumV1(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
```

### 1.1. Response

Enum 필드는 Json으로 변환될 때, 일반적으로 해당 필드의 변수 명이 key가 되고, Enum의 열거형 상수 명이 value가 됩니다.

```json
{
    "name": "test 1",
    "anEnum": "UNDER_SCORE"
}
```

### 1.2 Request

반대로 Json에서 Enum 필드로 변환될 때는, 열거형 상수 명 또는 순서를 이용하여 요청할 수 있습니다. 

- 이름으로 요청하는 경우, 열거형 상수 명을 사용합니다.
- 순서로 요청하는 경우, 열거형 상수가 정의된 순서를 사용합니다. (순서는 0부터 시작)

```json
// 열거형 상수 명으로 요청
{
    "name": "test 1",
    "anEnum": "UNDER_SCORE"
}
// 열거형 상수의 순서를 이용해서 요청
{
    "name": "test 1",
    "anEnum": 1
}
```

## 2. @JsonValue / @JsonCreator

[MyEnumV2.java](https://github.com/ch-yang1273/Playground/blob/master/enumjson/src/main/java/study/enumjson/MyEnumV2.java)

@JsonValue 어노테이션을 메서드에 적용하면, 해당 메서드가 반환하는 값을 Json의 value로 사용합니다.

- @JsonValue가 단독으로 있다면, Response와 Request 모두 이 메서드의 반환값을 value로 사용합니다.
- @JsonCreator 어노테이션을 사용하는 메서드도 함께 있다면, Request에서는 @JsonCreator가 적용된 메서드를 이용해서 Json의 value를 Enum 필드로 변환합니다.

```java
public enum MyEnumV2 {
    VALUE("value"),
    UNDER_SCORE("underscore")
    ;

    private final String value;

    MyEnumV2(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MyEnumV2 forValue(String value) {
        for (MyEnumV2 myEnumV2 : MyEnumV2.values()) {
            if (myEnumV2.getValue().equals(value)) {
                System.out.println("@JsonCreator: " + myEnumV2.getValue());
                return myEnumV2;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
```

### 2.1. Response

```json
{
    "name": "test 2",
    "anEnum": "underscore"
}
```

### 2.2. Request

@JsonCreator가 붙은 메서드에는 JSON의 value가 파라미터로 전달되고, 이 메서드는 value에 해당하는 Enum 상수를 반환합니다

```json
{
    "name": "test 2",
    "anEnum": "underscore"
}
```

## 3. @JsonFormat

[MyEnumV3.java](https://github.com/ch-yang1273/Playground/blob/master/enumjson/src/main/java/study/enumjson/MyEnumV3.java)

Enum 클래스에 ```@JsonFormat(shape = JsonFormat.Shape.OBJECT)``` 옵션을 적용하면, 해당 Enum 클래스의 모든 필드를 반환합니다.

이때, 필드에 대한 Getter가 없으면 에러 메시지도 없이 빈 객체가 반환하니 주의해야 합니다. (예: "enum": {} )

```java
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum MyEnumV3 {
    VALUE("value", 100),
    UNDER_SCORE("underscore", 200)
    ;

    private final String value;
    private final int num;

    MyEnumV3(String value, int num) {
        this.value = value;
        this.num = num;
    }
}
```

### 3.1. Response

```json
{
    "name": "test 3",
    "anEnum": {
        "value": "underscore",
        "num": 200
    }
}
```

## 4. @JsonProperty

[EnumDtoV3.java](https://github.com/ch-yang1273/Playground/blob/master/enumjson/src/main/java/study/enumjson/EnumDtoV3.java)

Enum에만 해당하는 내용은 아니지만, DTO 클래스의 필드에 @JsonProperty 어노테이션을 적용하면, Json의 Key 이름을 변경할 수 있습니다.

```java
@AllArgsConstructor
@Getter
public class EnumDtoV3 {

    private String name;

    @JsonProperty("enum")
    private MyEnumV3 anEnum;
}
```

### 4.1. Response

```json
{
    "name": "test 4",
    "enum": {
        "value": "underscore",
        "num": 200
    }
}
```
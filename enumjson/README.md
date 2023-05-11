# Enum 필드를 포함하는 Response, Request DTO 사용 테스트

이 프로젝트에서는 스프링의 RestController에서 기본 데이터 타입(primitive data type)이 아닌 Enum 필드를 포함하는 Response, Request DTO 사용에 대해서 테스트를 진행했습니다.

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

별다른 설정 없이 Enum 필드를 포함하는 DTO를 Response하면, 열거형 상수 명으로 반환됩니다.

```json
{
    "name": "test 1",
    "anEnum": "UNDER_SCORE"
}
```

### 1.2 Request

Request 할 때는 열거형 상수 명으로 요청을 하거나, 열거형 상수의 순서를 이용해서 요청을 할 수 있습니다.

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

@JsonValue을 메서드에 사용하면 메서드가 반환하는 값을 json의 value로 사용합니다.

@JsonValue만 있다면 Response, Request에서 모두 이 메서드의 반환 값을 value로 사용하지만,

@JsonCreator를 사용하는 메서드도 있다면 Request에서는 이 메서드를 이용해서 json의 value를 열거형 상수로 변환합니다.

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

@JsonCreator가 붙은 forValue의 파라미터로 json의 value가 넘어가고, 해당하는 열거형 상수를 반환합니다.

```json
{
    "name": "test 2",
    "anEnum": "underscore"
}
```

## 3. @JsonFormat

[MyEnumV3.java](https://github.com/ch-yang1273/Playground/blob/master/enumjson/src/main/java/study/enumjson/MyEnumV3.java)

Enum 클래스에 ```@JsonFormat(shape = JsonFormat.Shape.OBJECT)``` 옵션을 주면, Enum 클래스의 필드 전체를 반환합니다.

이 때 Getter가 없으면 에러도 없이 빈 객체가 나가는 것을 주의해야 합니다. ( "enum": {} )

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

Enum에만 해당하는 내용은 아니지만, DTO의 필드에 @JsonProperty를 사용하면, json의 Key 이름을 변경할 수 있습니다.

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
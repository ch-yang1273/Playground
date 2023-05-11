package study.enumjson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

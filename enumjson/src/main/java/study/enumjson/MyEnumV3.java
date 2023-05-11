package study.enumjson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

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

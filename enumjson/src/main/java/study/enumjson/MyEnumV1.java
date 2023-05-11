package study.enumjson;

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

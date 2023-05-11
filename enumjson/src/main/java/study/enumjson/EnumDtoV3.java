package study.enumjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnumDtoV3 {

    private String name;

    @JsonProperty("enum")
    private MyEnumV3 anEnum;
}

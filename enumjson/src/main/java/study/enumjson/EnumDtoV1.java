package study.enumjson;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnumDtoV1 {

    private String name;
    private MyEnumV1 anEnum;
}

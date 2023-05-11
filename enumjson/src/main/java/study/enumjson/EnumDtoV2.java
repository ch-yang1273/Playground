package study.enumjson;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnumDtoV2 {

    private String name;
    private MyEnumV2 anEnum;
}

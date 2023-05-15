package study.enumjson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EnumController {

    /**
     * 열거형 상수 명으로 반환이 된다.
     * {
     *     "name": "test 1",
     *     "anEnum": "UNDER_SCORE"
     * }
     */
    @GetMapping("/v1")
    public ResponseEntity<EnumDtoV1> testResponse() {
        return ResponseEntity.ok().body(new EnumDtoV1("test 1", MyEnumV1.UNDER_SCORE));
    }

    /**
     * Request도 동일하게 열거형 상수 명으로 요청해야 한다.
     * 아니면 열거 순서 대로 0, 1과 같이 숫자도 가능하다.
     * {
     *     "name": "test 1",
     *     "anEnum": "UNDER_SCORE"
     * }
     * {
     *     "name": "test 1",
     *     "anEnum": 0
     * }
     */
    @PostMapping("/v1")
    public void testRequest(@RequestBody EnumDtoV1 dto) {
        log.info("name={}", dto.getName());
        log.info("enum={}", dto.getAnEnum().getValue());

    }

    /**
     * @JsonValue이 붙은 메서드에서 반환하는 값으로 반환된다.
     * {
     *     "name": "test 1",
     *     "anEnum": "underscore"
     * }
     */
    @GetMapping("/v2")
    public ResponseEntity<EnumDtoV2> testResponseV2() {
        return ResponseEntity.ok().body(new EnumDtoV2("test 1", MyEnumV2.UNDER_SCORE));
    }

    /**
     * @JsonValue이 붙은 메서드만 있으면 메서드에서 반환하는 값으로 입력받을 수 있다.
     * @JsonCreator이 붙은 메서드가 있으면 입력 시 받은 값이 메서드의 파라미터로 들어가고,
     *   메서드에서 반환된 enum 값이 필드에 대입된다.
     */
    @PostMapping("/v2")
    public void testRequestV2(@RequestBody EnumDtoV2 dto) {
        log.info("name={}", dto.getName());
        log.info("enum={}", dto.getAnEnum().getValue());
    }

    /**
     * @JsonFormat(shape = JsonFormat.Shape.OBJECT)
     * 옵션을 주면 Enum 클래스의 필드 전체를 반환해줄 수 있다.
     * 이 때 Getter가 없으면 에러도 없이 빈 객체가 나가는 것 주의. "enum": {}
     * {
     *     "name": "test 1",
     *     "enum": {
     *         "value": "underscore",
     *         "num": 200
     *     }
     * }
     *
     * 그리고 key 형식을 바꿀 때는 Dto에서 @JsonProperty("text")을 사용하면 된다.
     */
    @GetMapping("/v3")
    public ResponseEntity<EnumDtoV3> testResponseV3() {
        return ResponseEntity.ok().body(new EnumDtoV3("test 1", MyEnumV3.UNDER_SCORE));
    }
}

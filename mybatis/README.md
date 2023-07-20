# Mybatis

## application.properties

```xml
# MyBatis
mybatis.type-aliases-package=com.example.mybatis.member.domain
mybatis.mapper-locations=classpath:com/example/mybatis/member/repository/*.xml
mybatis.configuration.map-underscore-to-camel-case=true
```

> mybatis.type-aliases-package

- 타입 별칭을 위한 패키지를 설정합니다.
- `com.example.mybatis.member.domain` 패키지 내의 모든 Java 빈 클래스들이 MyBatis에서 사용할 수 있는 타입 별칭으로 등록됩니다.

> com.example.mybatis.member.domain

- MyBatis 매퍼 파일의 위치를 설정하는 속성입니다.
- 패키지 내의 모든 XML 파일을 Mybatis의 매퍼 파일로 로드합니다.

> mybatis.configuration.map-underscore-to-camel-case

- 데이터베이스 컬럼명과 Java 필드명 사이의 매핑 전략을 설정합니다.
- true로 설정하면, 스네이크 케이스(underscore_separated)의 데이터베이스 컬럼 이름을 카멜 케이스(camelCase)의 Java 필드 이름으로 자동 매핑합니다.
- 데이터베이스의 user_name <-> Java의 userName 필드
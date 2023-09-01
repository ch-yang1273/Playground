# Spring REST Docs 사용 방법

## Dependency 추가 및 설정

```groovy
plugins {
   id 'java'
   id 'org.springframework.boot' version '2.7.15'
   id 'io.spring.dependency-management' version '1.0.15.RELEASE'

   // Gradle 플로그인을 추가. 
   // 이 플러그인은 .adoc(AsciiDoc) 형식의 문서를 HTML, PDF 등의 형식으로 변환해준다.
   // Gradle Tasks에 "asciidoctor"가 추가된 것을 확인할 수 있다. (아래 이미지 확인)
   id 'org.asciidoctor.jvm.convert' version '3.3.2'
}

group = 'com.study'
version = '0.0.1-SNAPSHOT'

java {
   sourceCompatibility = '11'
}

configurations {
   // Gradle configuration을 선언
   // 이를 통해 추가적인 Asciidoctor 의존성을 설정한다.
   asciidoctorExtensions
   
   compileOnly {
      extendsFrom annotationProcessor
   }
}

repositories {
   mavenCentral()
}

// build.gradle에서 사용할 전역 변수를 설정
// `snippetsDir` 변수를 "build/generated-snippets"으로 지정했다.
ext {
   set('snippetsDir', file("build/generated-snippets"))
}

dependencies {
   implementation 'org.springframework.boot:spring-boot-starter-web'
   compileOnly 'org.projectlombok:lombok'
   annotationProcessor 'org.projectlombok:lombok'

   // Spring REST Docs의 Asciidoctor 확장 의존성을 추가
   // 이 확장을 추가해야 adoc 문서에 {snippets}를 사용할 수 있었다.
   asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'

   testImplementation 'org.springframework.boot:spring-boot-starter-test'
   
   // 테스트에서 사용할 spring-restdocs-mockmvc 의존성을 추가
   // MockMvc 테스트 결과를 API 문서로 자동 생성할 수 있다.
   // Spring Initializr에서 "Spring REST Docs" 의존성을 추가하면 이 설정만 있다. 
   testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
}

tasks.named('test') {
   // "test" task의 출력 디렉토리를 `snippetsDir`로 지정
   // Spring REST Docs가 생성 코드 스니펫들이 이 디렉토리에 저장된다.
   outputs.dir snippetsDir
   useJUnitPlatform()
}

// "asciidoctor" task를 정의
// tasks.named('asciidoctor')는 warning이 발생하더라...
asciidoctor {
   configurations 'asciidoctorExtensions'
   baseDirFollowsSourceFile()
   inputs.dir snippetsDir
   dependsOn test
}

// asciidoctor 작업 실행 전 기존 생성된 문서를 삭제
asciidoctor.doFirst {
   delete file('src/main/resources/static/docs')
}

// asciidoctor가 생성한 문서 (html)을 static 폴더로 복사
tasks.register('copyDocument', Copy) {
   dependsOn asciidoctor

   from "${asciidoctor.outputDir}"
   into file("src/main/resources/static/docs")
}

// build 작업 수행 시 copyDocument 수행
build {
   dependsOn copyDocument
}
```

### gradlew tasks --all
![GradleTasks](https://user-images.githubusercontent.com/61798028/265035285-b8e190d9-a706-4c9a-b595-69a7ff52538a.png)

## test 수행
- build/generated-snippets 디렉토리에 생성 된 .adoc(Asciidoc) 문서들이 생성 된다.

## asciidoctor
gradlew asciidoctor 수행 시 `src/docs/asciidoc` 경로에 작성한 문서에 따라 html 문서를 생성한다.

생성된 문서(html)는 `build/docs/asciidoc` 위치에 생성되고, 위 설정에서 "${asciidoctor.outputDir}"가 이 위치에 해당한다.

## src/docs/asciidoc
asciidoctor는 이 경로에 있는 .adoc 파일만 인식해서 html 문서를 생성해 준다. index.adoc은 다음과 같이 작성했으나, 작성 방법은 좀더 알아봐야겠다.

```asciidoc
= API Documentation
:toc: left
:icons: font
:doctype: book

== API

=== Something

==== Request
include::{snippets}/http-request.adoc[]

==== Response
include::{snippets}/http-response.adoc[]
```
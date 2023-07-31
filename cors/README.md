# CORS 개념과 Spring 서버에서의 CORS 테스트

## 동일 출처 정책 (Same-Origin Policy)

CORS를 이해하기 위해서는 먼저 동일 출처 정책(SOP)에 대해 알아야 한다.

이 정책은 보안을 위해 <U>웹 브라우저가</U> 자바스크립트 등을 통해 액세스할 수 있는 리소스의 범위를 자신과 동일한 출처로 제한한다.

동일 출처(Same-Origin)라는 것은 프로트콜(http, https), 도메인(example.com), 포트(8080)가 모두 동일한 경우를 뜻한다.
예를 들어, `https://example.com:8080`에서 로드된 페이지는 기본적으로 `https://example.com:8080`에서 제공하는 리소스만 액세스 할 수 있다.

웹 브라우저는 클라이언트의 Request Header와 서버의 Response Header를 비교하여 동일 출처가 맞는지 확인하여 액세스를 허용할지 결정한다.

## CORS (교차 출처 리소스 공유 : Cross-Origin Resource Sharing) 개념

동일 출처 정책에 따르면 웹 페이지는 자신과 동일한 출처의 리소스만 요청이 가능하다.
하지만 다양한 이유로 이 정책을 우회해야 하는 경우가 있는데, 이 때 사용되는 방법을 CORS라고 한다.

## CORS 원리

브라우저는 다른 Origin에 접근해야 할 때, 서버의 CORS 정책을 먼저 확인하여 액세스 허용 여부를 결정한다.

즉, CORS 정책은 응답하는 서버에서 설정 하지만, 최종적인 액세스 허용 여부는 브라우저가 서버가 응답한 `CORS 헤더`로부터 CORS 정책을 보고 결정한다.
그래서 Postman과 브라우저의 요청에 대한 응답이 다르게 보일 수 있다.

서버에 CORS 정책 정보를 요청하는 방법에는 `Simple Request`와 `Preflight Request`가 있다.

### Simple Request

Request가 다음의 조건을 만족하면 브라우저는 사전 요청 없이 바로 실제 요청을 전송한다. 
서버는 이 요청에 대한 응답으로 `CORS 헤더`를 포함할 수 있는데, 브라우저는 이 응답의 CORS 정책을 검토하여 리소스에 대한 액세스를 허용할지 결정한다.

- 요청 메서드는  GET, HEAD, POST 중 하나
- 요청 헤더는 Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width만 사용
- Content-Type은 application/x-www-form-urlencoded, multipart/form-data, text/plain 중 하나

Authorization 헤더도 사용할 수 없고, Content-Type으로 application/json을 사용할 수도 없어 대부분의 요청에서는 조건을 만족하기 어렵다.

### Preflight Request (사전 요청)

Simple Request의 조건을 만족하지 못하면 브라우저는 실제 요청을 전송하기 전에 `Preflight Request`를 먼저 전송한다.

`Preflight Request`는 <U>OPTIONS</U> 메서드를 사용하고, 헤더에 다음의 정보를 포함한다.

- Access-Control-Request-Method: 실제 요청에서 사용하려는 HTTP 메서드
- Access-Control-Request-Headers: 실제 요청에서 사용하려는 커스텀 헤더의 Key 리스트
- Origin: 요청을 보내는 페이지의 출처

`Preflight Request`를 사용하면 요청이 CORS 정책을 위반 했는지 사전에 알 수 있어,
유효하지 않은 요청을 주고 받는 불필요한 리소스 낭비를 하지 않을 수 있다.

### Preflight Request 응답 헤더

Preflight Request의 응답 헤더에서는 CORS 관련해서 다음의 헤더를 확인해야 한다.

- Access-Control-Allow-Origin: 서버가 요청을 받아들일 수 있는 출처(origin)
- Access-Control-Allow-Methods: 서버가 허용하는 HTTP 메소드
- Access-Control-Allow-Headers: 서버가 허용하는 요청 헤더
- Access-Control-Allow-Credentials : 서버가 크리덴셜을 허용하는지를 나타낸다.
- Access-Control-Max-Age : preflight 요청의 결과를 캐시(cache)할 수 있는 최대 시간을 초 단위로 나타낸다.

## Spring 서버의 Cors 정책 설정

Spring Security에서 CORS를 설정하기 위해서는 `CorsConfiguration`를 설정해야하는데 다음과 같이 설정할 수 있다.

이 테스트에서는 모든 접근에 대해 와일드 카드로 설정했다.

```java
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .cors().configurationSource(corsConfigurationSource())
        ;
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
```

- configuration.addAllowedOrigin("*") : 모든 출처에서의 요청을 허용
- configuration.addAllowedMethod("*") : 모든 HTTP 메서드를 허용
- configuration.addAllowedHeader("*") : 모든 HTTP 헤더를 허용
- configuration.setMaxAge(3600L) : Preflight 요청의 결과를 1시간 동안 캐싱
- configuration.setAllowCredentials(true) : Credentials 정보를 허용


- UrlBasedCorsConfigurationSource : CORS 설정 정보를 URL 패턴에 적용하는 구현체

※ Credentials

여기서 Credentials은 쿠키, 인증 헤더(Authentication) 등을 말한다.
setAllowCredentials(true)로 설정하면 이런 Credentials를 포함한 요청을 보낼 수 있게 된다.

응답 헤더에는 `Access-Control-Allow-Credentials`를 true로 설정하여, Credentials를 허용함을 알릴 수 있다.

setAllowCredentials()를 true로 설정하면 `configuration.addAllowedOrigin("*")`에서 에러가 발생하는데,
이는 CORS 명세에서 `Access-Control-Allow-Credentials`가 true 일 때 `Access-Control-Allow-Origin`이 와일드 카드("*")가 될 수 없음을 명시 했기 때문이다.

이 설정을 true로 허용하면 CSRF와 같은 공격에 노출될 위험이 있으니 주의해야 한다.

- 참고: 교차 출처 리소스 공유(MDN) - [자격 증명을 포함한 요청](https://developer.mozilla.org/ko/docs/Web/HTTP/CORS#%EC%9E%90%EA%B2%A9_%EC%A6%9D%EB%AA%85%EC%9D%84_%ED%8F%AC%ED%95%A8%ED%95%9C_%EC%9A%94%EC%B2%AD)

## CORS 테스트

CORS 테스트를 위해서는 출처가 다른 두 개의 서버가 필요하다.

- 클라이언트 역할을 하는 웹 서버
- CORS 설정이 적용된 서버

이 테스트에서는 클라이언트 서버는 `https://test-cors.org`를 사용하고, SpringBoot로 API 서버만 구성한다.

SpringBoot의 Dependency는 `Web`과 `Security`만 추가한다.

※ [https://test-cors.org](https://test-cors.org) 사이트를 이용하면,
CORS 테스트를 위한 클라이언트 서버를 따로 구성하지 않아도 되서 편하다. localhost 서버도 테스트가 가능하다.
<U>http</U>://test-cors.org 페이지에서는 테스트가 정상적으로 되지 않으니 프로토콜이 `https`가 맞는지 확인!

CORS 테스트의 요청과 응답은 `https://test-cors.org` 페이지에서 개발자 모드(F12)로 확인한다.

### 1. https://test-cors.org Client 페이지에서 Request 설정

Mapping 될 Remote URL을 설정하고, Custom Header의 허용 여부도 테스트하기 위해 `key: value` 헤더도 추가

![test-cors](https://user-images.githubusercontent.com/61798028/257121177-f1c3f641-0d7f-4a0a-bfd8-148321cf2cb3.png)

### 2. CORS 정책 설정 없이 테스트

CORS 정책을 따로 설정하지 않고, 다른 출처의 리소스를 요청하여 CORS 정책을 위반하는 테스트를 했다.

다른 출처에 대한 리소스 요청이므로, 브라우저는 먼저 `Preflight Request`를 전송한다.

`Preflight Request`는 상태코드 403을 반환 받았고, 실제 요청은 전송하지 않았다.

[Preflight Request 헤더]

`Access-Control-Request-Method`, `Access-Control-Request-Headers`, `Origin` 헤더 및 메서드 확인!

![Request 헤더](https://user-images.githubusercontent.com/61798028/257121162-5194704a-cdbb-4d49-98b6-d1d58befe61a.png)

[Preflight Response 헤더]

![Response 헤더](https://user-images.githubusercontent.com/61798028/257121166-655d2b38-e872-44cc-8644-4c4e06beefa5.png)

[Console 출력]

CORS policy 위반으로 요청이 Block 되었다는 메시지를 확인할 수 있다.

![Console 출력](https://user-images.githubusercontent.com/61798028/257121169-753fd0b0-7a59-436a-8fe3-756e4ce9e792.png)

### 3. CORS 정책 설정 테스트

모든 CORS 정책을 와일드 카드로 풀고 테스트를 진행했다. (Credentials는 허용하지 않음)

`Preflight Request`는 상태 코드 200을 반환 받았고, 이어서 실제 요청도 전송했다.

[Preflight Request 헤더]

`Access-Control-Request-Method`, `Access-Control-Request-Headers`, `Origin` 헤더 및 메서드 확인!

![Request 헤더](https://user-images.githubusercontent.com/61798028/257121170-0461eb26-775f-4caa-98ea-708cd1669444.png)

[Preflight Response 헤더]

`Access-Control-Allow-Origin`, `Access-Control-Allow-Methods`, `Access-Control-Allow-Headers`, ~~`Access-Control-Allow-Credentials`~~, `Access-Control-Max-Age` 헤더 확인

![Response 헤더](https://user-images.githubusercontent.com/61798028/257121173-1234a14c-f6c3-417c-ad60-0babb07c73a4.png)

[실제 Request 헤더 - GET]

![GET Request](https://user-images.githubusercontent.com/61798028/257121178-2a1ae0a4-05cf-46e1-8a0f-d7ebb26f151e.png)

[실제 Response 헤더 - GET]

![GET Response](https://user-images.githubusercontent.com/61798028/257121182-c1430b7b-48b8-4b83-a24d-802c0adf88eb.png)
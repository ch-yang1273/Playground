# CORS (Cross-Origin Resource Sharing)

## 동일 출처 정책 (Same-Origin Policy)

CORS를 이해하기 위해서는 먼저 동일 출처 정책(SOP)에 대해 알아야 한다.

이 정책은 보안을 위해 <U>웹 브라우저가</U> 자바스크립트 등을 통해 액세스할 수 있는 리소스의 범위를 자신과 동일한 출처로 제한한다.

동일 출처(Same-Origin)라는 것은 프로트콜(http, https), 도메인(example.com), 포트(8080)가 모두 동일한 경우를 뜻한다.
예를 들어, `https://example.com:8080`에서 로드된 페이지는 기본적으로 `https://example.com:8080`에서 제공하는 리소스만 액세스 할 수 있다.

웹 브라우저는 클라이언트의 Request Header와 서버의 Response Header를 비교하여 동일 출처가 맞는지 확인하여 액세스를 허용할지 결정한다.

## CORS 개념

동일 출처 정책에 따르면 웹 페이지는 자신과 동일한 출처의 리소스만 요청이 가능하다.
하지만 다양한 이유로 이 동일 출처 정책을 우회해야 하는 경우가 있는데, 이를 CORS라고 한다.

## CORS 원리

브라우저는 다른 Origin에 접근해야 할 때, 서버의 CORS 정책을 `CORS 헤더`로부터 먼저 확인하여 액세스 허용 여부를 결정한다.
이 서버의 CORS 정책을 알기 위한 CORS 요청 방법에는 `Simple Request`와 `Preflight Request`가 있다.

### Simple Request

Request가 다음의 조건을 만족하면 브라우저는 사전 요청 없이 바로 실제 요청을 전송한다. 
서버는 이 요청에 대한 응답으로 `CORS 헤더`를 포함할 수 있는데, 브라우저는 이 응답의 CORS 정책을 검토하여 리소스에 대한 액세스를 허용할지 결정한다.

- 요청 메서드는  GET, HEAD, POST 중 하나
- 요청 헤더는 Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width만 사용
- Content-Type은 application/x-www-form-urlencoded, multipart/form-data, text/plain 중 하나

Authorization 헤더도 사용할 수 없고, Content-Type으로 application/json을 사용할 수도 없어 대부분의 요청에서는 만족하기 어렵다.

### Preflight Request (사전 요청)

Simple Request의 조건을 만족하지 못하면 브라우저는 실제 요청을 전송하기 전에 `Preflight Request`를 먼저 전송한다.

`Preflight Request`는 <U>OPTIONS</U> 메서드를 사용하고, 헤더에 다음의 정보를 포함한다.

- Access-Control-Request-Method : 실제 요청에서 사용하려는 HTTP 메서드
- Access-Control-Request-Headers : 실제 요청에서 사용하려는 커스텀 헤더의 Key 리스트
- Origin : 요청을 보내는 페이지의 출처

`Preflight Request`를 사용하면 요청이 CORS 정책을 위반 했는지 사전에 알 수 있어,
유효하지 않은 요청을 주고 받는 불필요한 리소스 낭비를 하지 않을 수 있다.
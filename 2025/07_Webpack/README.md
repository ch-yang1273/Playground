# Webpack 프로젝트

Webpack을 사용한 Bundling 프로젝트

## 서버 실행 테스트

```bash
npx http-server ./ 8080
```

## 변경 감지 컴파일

```bas
npx webpack --watch
```

## 명령어로 번들링

```bash
npx webpack --entry ./src/index.js --output ./public/index_bundle.js
```

## webpack 설정 파일과 번들링

- `webpack.config.js`
- `webpack.config.prod.js`

### 개발 모드로 실행하기

- 개발 중에는 다음 명령어로 Webpack을 실행
- 이 명령어는 개발 모드로 빌드하며, 소스맵을 포함하여 디버깅에 유용한 정보를 제공함

```bash
npx webpack
```

### 프로덕션 빌드

- 배포를 위한 프로덕션 빌드를 생성하려면 다음 명령어를 사용
- 이 명령어는 코드 최적화가 적용된 프로덕션용 번들을 생성함

```bash
npx webpack --config webpack.config.prod.js
```

## 프로젝트 구조

- `src/`: 소스 코드 디렉토리
  - `index.js`: 애플리케이션 진입점
  - 기타 모듈 파일들
- `public/`: 빌드 출력 디렉토리
  - `index_bundle.js`: 번들된 자바스크립트 파일
- `index.html`: 메인 HTML 파일

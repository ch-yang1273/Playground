# SASS

## 개발 환경 설정

### node-sass 설치 및 설정

#### LTS 버전의 Node 설치

npm이 같이 깔린다.

```Shell
node -v
# v20.9.0
npm -v 
# 10.1.0
```

#### node-sass 설치

Document : [node-sass - npm (npmjs.com)](https://www.npmjs.com/package/node-sass)

```shell
# package.json이 생성된다.
npm init -y

# node-sass 설치
npm i node-sass
```

#### package.json의 scripts 수정

참고 : [node-sass#command-line-interface](https://www.npmjs.com/package/node-sass#command-line-interface)

```json
"scripts": {
  "test": "echo \"Error: no test specified\" && exit 1",
  "node-sass": "node-sass",
  "sass": "node-sass styles/main.scss ./style.css"
},
```

실행

```shell
npm run test
npm run node-sass
npm run sass
```

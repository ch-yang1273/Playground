# React Study

## 리액트 프로젝트 생성

```bash
npx create-react-app study
```

### 프로젝트 생성 에러

```bash
λ npx create-react-app study
npm ERR! code ENOENT
npm ERR! syscall lstat
npm ERR! path C:\Users\username\AppData\Roaming\npm
npm ERR! errno -4058
npm ERR! enoent ENOENT: no such file or directory, lstat 'C:\Users\username\AppData\Roaming\npm'
npm ERR! enoent This is related to npm not being able to find a file.
npm ERR! enoent
```

C:\Users\username\AppData\Roaming\ 경로에 npm 폴더 하나 만들어주면 된다.

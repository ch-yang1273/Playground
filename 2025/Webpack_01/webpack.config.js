// Node.js의 path 모듈을 불러옵니다. 파일 경로를 다루기 위해 사용됩니다.
const path = require('path');

// 웹팩 설정을 내보냅니다.
module.exports = {
    // 진입점(entry point)을 설정합니다. 웹팩이 번들링을 시작할 파일을 지정합니다.
    entry: './src/index.js',
    
    // 번들된 파일의 출력 설정
    output: {
        // 번들 파일이 생성될 경로를 절대 경로로 지정합니다.
        path: path.resolve(__dirname, 'public'),
        // 번들 파일의 이름을 지정합니다.
        filename: 'index_bundle.js',
    },
    
    // 빌드 모드를 설정합니다. 'development'는 개발용 최적화를 적용합니다.
    mode: 'development',
    
    // 모듈에 대한 설정
    module: {
        // 로더(Loader)에 대한 규칙을 정의합니다.
        rules: [
            {
                // .css 확장자를 가진 파일들을 처리하기 위한 규칙
                test: /\.css$/,
                // 사용할 로더들을 지정합니다. 오른쪽에서 왼쪽 순서로 실행됩니다.
                use: [
                    'style-loader', // CSS를 <style> 태그로 DOM에 주입
                    'css-loader'    // CSS 파일을 자바스크립트 모듈로 변환
                ],
            },
        ],
    },
};
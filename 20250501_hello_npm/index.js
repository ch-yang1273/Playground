#!/usr/bin/env node

// ESM 방식의 모듈 사용
import { createRequire } from 'module';
const require = createRequire(import.meta.url);

// 패키지 정보 가져오기 (ESM에서의 방식)
const packageJson = require('./package.json');

console.log('Hello, World!');
console.log(`Package: ${packageJson.name}`);
console.log(`Version: ${packageJson.version}`);

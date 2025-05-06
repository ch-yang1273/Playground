import { pipe, range, map, filter, take } from '@fxts/core';

// 시간 측정 함수
function measureTime<T>(name: string, fn: () => T): T {
  const startTime = performance.now();
  const result = fn();
  const endTime = performance.now();
  console.log(`${name} 실행 시간: ${(endTime - startTime).toFixed(2)}ms`);
  return result;
}

// 지연 평가를 사용한 예제
function processWithLazy() {
  const numbers = range(1, 1000);
  
  // 지연 평가의 동작을 확인하기 위한 로그 출력
  console.log('=== 지연 평가 동작 확인 ===');
  
  // pipe를 사용하여 타입 안전하게 연산
  const result = pipe(
    numbers,
    map((n: number) => {
      console.log(`제곱: ${n} -> ${n * n}`);
      return n * n;
    }),
    filter((n: number) => {
      console.log(`필터링: ${n} (짝수: ${n % 2 === 0})`);
      return n % 2 === 0;
    }),
    take(10)
  );
  
  // Array.from을 호출하기 전까지는 실제로 계산이 이루어지지 않음
  console.log('\n=== Array.from 호출 전 상태 ===');
  console.log('result:', result);
  
  // Array.from을 호출하면 실제로 계산이 시작됨
  console.log('\n=== Array.from 호출 후 계산 시작 ===');
  const resultArray = Array.from(result);
  console.log('\n=== 최종 결과 ===');
  console.log('지연 평가 결과:', resultArray);
  
  return resultArray;
}

// 지연 평가를 사용하지 않는 예제
function processWithoutLazy() {
  console.log('=== 비지연 평가 동작 확인 ===');
  
  // 배열 생성
  console.log('\n=== 배열 생성 시작 ===');
  const numbers = Array.from({ length: 1000 }, (_, i) => {
    const num = i + 1;
    console.log(`생성: ${num}`);
    return num;
  });
  
  // 제곱 연산
  console.log('\n=== 제곱 연산 시작 ===');
  const squared = numbers.map(n => {
    const result = n * n;
    console.log(`제곱: ${n} -> ${result}`);
    return result;
  });
  
  // 짝수 필터링
  console.log('\n=== 짝수 필터링 시작 ===');
  const evenNumbers = squared.filter(n => {
    const isEven = n % 2 === 0;
    console.log(`필터링: ${n} (짝수: ${isEven})`);
    return isEven;
  });
  
  // 10개 선택
  console.log('\n=== 10개 선택 시작 ===');
  const top10 = evenNumbers.slice(0, 10);
  
  console.log('\n=== 최종 결과 ===');
  console.log('비지연 평가 결과:', top10);
  return top10;
}

// 프로그램 실행
function main() {
  try {
    console.log('=== 지연 평가 예제 실행 ===');
    measureTime('지연 평가', processWithLazy);
    
    console.log('\n=== 비지연 평가 예제 실행 ===');
    measureTime('비지연 평가', processWithoutLazy);
  } catch (error) {
    console.error('오류 발생:', error);
  }
}

main();

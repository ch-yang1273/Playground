# SQL Window Function

## Window 함수 사용법

```sql
<Window function>(<expression>) OVER (
    [PARTITION BY <expression list>]
    [ORDER BY <expression list>]
)
```

- PARTITION BY : 데이터를 특정 조건에 따라 그룹으로 분할하는 점에서 GROUP BY와 비슷하다.
- ORDER BY : `Window` 내에서 행의 순서를 정의할 때 사용된다. RANK() 함수를 보면 알 수 있다.

## 주로 사용되는 Window 함수

RANK()
 - 각 그룹 내에서 현재 행의 순위를 반환한다. 동일한 값이 있을 경우 동일한 순위를 부여하고, 그 다음 순위는 건너뛴다.
 - 예를 들어, 1, 2, 2, 4의 순위를 부여한다.
 
DENSE_RANK()
 - RANK()와 유사하나, 중복 값이 있어도 다음 순위를 건너뛰지 않는다.
 - 예를 들어, 1, 2, 2, 3의 순위를 부여한다.
 
ROW_NUMBER()
 - 각 그룹 내에서 유일한 순위를 부여한다. 동일한 값이 있어도 순위가 유일하다.
 - 예를 들어 주어진 값이 10, 20, 20, 30이라면, 1, 2, 3, 4 순위(?)를 부여한다.

SUM(), AVG(), MIN(), MAX()
 - Window 내에서 각각 합계, 평균, 최소, 최대를 계산한다.
 - SUM()은 총합이 아닌 순서에 따른 <U>누적합</U>인 것을 주의
 
LEAD(컬럼), LAG(컬럼)
 - LEAD : 다음 행의 값을 반환한다.
 - LAG : 이전 행의 값을 반환한다.
 - LEAD(컬럼, 칸 수)와 같이 칸 수 만큼 다음 행의 값을 반환할 수도 있다.

## GROUP BY절의 집계 함수와 Window 함수의 차이

### GROUP BY절의 집계 함수

GROUP BY절을 사용하면 기준에 따라 그룹을 분류하고, 각 그룹에 대한 집계 함수(SUM, AVG, MAX, MIN 등)를 적용하여 하나의 결과 값을 반환한다.

이 방식의 결과 집합은 <U>원래 데이터의 행 수보다 작거나 같다.</U>

[Input]

| id | num |
| -- | --- |
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |

[쿼리]

```sql
SELECT a.num, COUNT(*) as count
FROM Logs a
GROUP BY a.num
```

[Output]

| num | count |
| --- | ----- |
| 1   | 4     |
| 2   | 3     |

[결과 분석]

같은 a.num에 대해서 그룹을 짓고, 각 그룹에 집계 함수(COUNT)를 적용하고 각 그룹당 하나의 결과 값인 count를 반환했다.


### Window 함수
Window 함수를 사용하면, 각 행에 대해 정의된 `Window` 내의 데이터에 집계 함수를 적용하여 해당 행에 반환한다. `Window`는 현재 행을 기준으로 한 데이터의 하위 집합이며 PARTITION BY, ORDER BY, ROWS 등의 절로 정의된다. 

이 방식으로 결과 집합의 행 수는 <U>원래 데이터의 행 수와 동일하다.</U>

[Input]

위와 같음.

[쿼리]

```sql
SELECT *, COUNT(*) OVER (PARTITION BY a.num) as count
FROM Logs a
ORDER BY a.id
```

[Output]

| id | num | count |
| -- | --- | ----- |
| 1  | 1   | 4     |
| 2  | 1   | 4     |
| 3  | 1   | 4     |
| 4  | 2   | 3     |
| 5  | 1   | 4     |
| 6  | 2   | 3     |
| 7  | 2   | 3     |

[결과 분석]

동일한 a.num을 갖는 그룹을 `Window`로 설정하고, 이 `Window`에 대해 COUNT 함수를 적용 후 결과를 각 행의 기준에 맞게 반환했다.
SQL Windows Function

## `GROUP BY`절과 집계 함수와 Window 함수의 차이

### `GROUP BY`절과 집계 함수

`GROUP BY`절을 사용하면 기준에 따라 그룹을 분류하고, 각 그룹에 대한 집계 함수(SUM, AVG, MAX, MIN 등)를 적용하여 하나의 결과 값을 반환한다. 이 방식의 결과 집합은 원래 데이터의 행 수보다 작거나 같다.

[Input]

+----+-----+
| id | num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+

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
Window 함수를 사용하면, 각 행에 대해 정의된 `Window` 내의 데이터에 집계 함수를 적용하여 해당 행에 반환한다. `Window`는 현재 행을 기준으로 한 데이터의 하위 집합이며 PARTITION BY, ORDER BY, ROWS 등의 절로 정의된다. 이 방식으로 결과 집합의 행 수는 원래 데이터의 행 수와 동일하다.

[Input]

-- 위와 같음.

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

동일한 a.num을 갖는 그룹을 `Window`로 설정하고, 이 `Window`에 대해 COUNT 함수를 적용했다. 그리고 그 결과를 각 행의 기준에 맞게 반환했다.
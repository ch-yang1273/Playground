{
  /**
   * Enum
   */

  // JavaScript
  const MAX_NUM = 6;
  const MAX_STUDENT_PER_CLASS = 10;
  const MONDAY = 0;
  const TUESDAY = 1;
  const WEDNESDAY = 2;

  const DAYS_ENUM = Object.freeze({"MONDAY": 0, "TUESDAY": 1, "WEDNESDAY": 2,});
  const dayOfToday = DAYS_ENUM.MONDAY; // 0

  // TypeScript
  enum Days {
    Monday,    // 0
    Tuesday,   // 1
    Wednesday, // 2
    // ...     // 하나 씩 증가 (문자열도 대입 가능)
  }
  console.log(Days.Wednesday);

  let today: Days;
  // Type '10' is not assignable to type 'Days'
  //today = 10; // 강의와 달리 TS 5.0부터 에러가 발생한다.
}
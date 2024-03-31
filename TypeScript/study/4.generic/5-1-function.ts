{
  // bad case
  function checkNumberNotNull(arg: number | null): number {
    if (arg == null) {
      throw new Error('not valid number!');
    }
    return arg;
  }

  // bad case
  function checkAnyNotNull(arg: any | null): number {
    if (arg == null) {
      throw new Error('not valid number!');
    }
    return arg; // 반환 타입이 타입을 잃고 'any' 타입이 된다.
  }

  // Generic
  function checkNotNull<GENERIC>(arg: GENERIC | null): GENERIC {
    if (arg == null) {
      throw new Error('not valid number!');
    }
    return arg; // 반환 타입이 타입을 잃고 'any' 타입이 된다.
  }

  const result1 = checkNotNull(123); // 사용할 때 타입이 정해진다.
  const result2: boolean = checkNotNull(true); // 사용할 때 타입이 정해진다.
}
{
  /**
   * 절차지향적으로 프로그래밍
   */
  // 커피 콩을 갖는 전역 변수
  let coffeeBeans: number = 50;
  const BEANS_PER_SHOT: number = 7;

  type CoffeeCup = {
    shots: number;
    hasMilk: boolean;
  }
  // 커피 콩으로 커피를 만드는 함수 (얼마나 많은 shot을 만들지)
  const makeCoffee = function (shots: number): CoffeeCup {
    if (coffeeBeans < BEANS_PER_SHOT * shots) {
      throw new Error('Not enough coffee beans!');
    }

    coffeeBeans -= BEANS_PER_SHOT * shots;
    return {
      shots: shots,
      hasMilk: false,
    }
  }

  coffeeBeans += 100;
  const coffee = makeCoffee(10);
  console.log(coffee);
}
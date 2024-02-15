{
  type CoffeeCup = {
    shots: number;
    hasMilk: boolean;
  }

  class CoffeeMaker {
    // 'BEANS_PER_SHOT'는 모든 객체가 굳이 따로 갖고 있을 필요 없다 (메모리 낭비)
    static BEANS_PER_SHOT: number = 7; // class level
    coffeeBeans: number = 0; // instance (obejct) level

    constructor(coffeeBeans: number) {
      this.coffeeBeans = coffeeBeans;
    }

    static makeMachine(coffeeBeans: number): CoffeeMaker {
      return new CoffeeMaker(coffeeBeans);
    }

    makeCoffee(shots: number): CoffeeCup {
      if (this.coffeeBeans < shots * CoffeeMaker.BEANS_PER_SHOT) {
        throw new Error('Not enough coffee beans!');
      }

      this.coffeeBeans -= shots * CoffeeMaker.BEANS_PER_SHOT;
      return {
        shots,
        hasMilk: false,
      };
    }
  }

  const coffeeMaker = new CoffeeMaker(50);
  console.log(coffeeMaker);
  const coffeeMaker2 = CoffeeMaker.makeMachine(100); // class level 함수 호출 (static)
  console.log(coffeeMaker2);

  const coffee: CoffeeCup = coffeeMaker.makeCoffee(3);
  console.log(coffee);
}

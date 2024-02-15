{
  type CoffeeCup = {
    shots: number;
    hasMilk: boolean;
  }

  // public : default 접근자
  // private
  // protectied
  class CoffeeMaker {
    // 'BEANS_PER_SHOT'는 모든 객체가 굳이 따로 갖고 있을 필요 없다 (메모리 낭비)
    private static BEANS_PER_SHOT: number = 7; // class level
    private coffeeBeans: number = 0; // instance (obejct) level

    private constructor(coffeeBeans: number) { // constructor 안쓰려고 함.
      this.coffeeBeans = coffeeBeans;
    }

    static makeMachine(coffeeBeans: number): CoffeeMaker {
      return new CoffeeMaker(coffeeBeans);
    }

    fillCoffeeBeans(beans: number) {
      if (beans < 0) {
        throw new Error('value of beans should be greater than 0');
      }
      this.coffeeBeans += beans;
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
  //coffeeMaker.coffeeBeans = -32;
  const coffeeMaker2 = CoffeeMaker.makeMachine(100); // class level 함수 호출 (static)
  console.log(coffeeMaker2);

  const coffee: CoffeeCup = coffeeMaker.makeCoffee(3);
  console.log(coffee);
}

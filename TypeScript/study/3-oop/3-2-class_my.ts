{
  class CoffeeCup {
    // 속성
    shots: number;
    hasMilk: boolean;

    constructor(shots: number, hasMilk: boolean) {
      this.shots = shots;
      this.hasMilk = hasMilk;
    }
  }
  class CoffeeMachine {
    private static coffeeBeans: number = 50;
    private BEANS_PER_SHOT: number = 7;

    makeCoffee(shots: number): CoffeeCup {
      if (CoffeeMachine.coffeeBeans < shots * this.BEANS_PER_SHOT) {
        throw new Error('Not enough coffee beans!')
      }

      CoffeeMachine.coffeeBeans -= shots * this.BEANS_PER_SHOT;
      return new CoffeeCup(shots, false);
    }
  }

  const machine = new CoffeeMachine();
  const coffee = machine.makeCoffee(3);
  console.log(coffee);
}

{
  /**
   * Union Types: OR
   */
  type Direction = 'left' | 'right' | 'up' | 'down';

  function move(direction: Direction) {
    console.log(direction);
  }

  move('left');

  type TileSize = 8 | 16 | 32;
  const size1: TileSize = 8; // (O)
  // const size2: TileSize = 10; (X)

  type SuccessState = {
    result: 'success';
    response: {
      body: string;
    };
  }
  type FailState = {
    result: 'fail';
    reason : string;
  };
  type LoginState = SuccessState | FailState;

  function login(): LoginState {
    return {
      result: 'success',
      response: {
        body: 'logged in!',
      }
    }
  }

  // function printLoginState(state: LoginState) {
  //   if ('response' in state) {
  //     console.log(state.response.body);
  //   } else {
  //     console.log(state.reason);
  //   }
  // }

  function printLoginState(state: LoginState) {
    if (state.result === 'success') {
      console.log(state.response.body);
    } else {
      console.log(state.reason);
    }
  }

  type Circle = { type: 'circle'; radius: number; };
  type Square = { type: 'square'; size: number; };
  type Shape = Circle | Square;

  function getArea(shape: Shape) {
    switch (shape.type) {
      case 'circle':
        return Math.PI * shape.radius ** 2;
      case 'square':
        return shape.size * shape.size;
    }
  }
}
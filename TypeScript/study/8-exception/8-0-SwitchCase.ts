{
  const position = { x: 0, y: 0 };

  function move(direction: 'up' | 'down' | 'left' | 'right') {
    switch (direction) {
      case "up":
        position.y += 1;
        break;
      case "down":
        position.y -= 1;
        break;
      case "left":
        position.x -= 1;
        break;
      case "right":
        position.x += 1;
        break;
      default:
        // 여기에 할당되는 case가 남으면 컴파일 에러 발생
        const invalid: never = direction;
        throw new Error(`Unknown Direction ${direction}`);
    }
  }
}
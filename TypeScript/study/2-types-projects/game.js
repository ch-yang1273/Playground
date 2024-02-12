var position = {
    x: 0,
    y: 0,
};
var move = function (dirrection) {
    switch (dirrection) {
        case "up":
            position.y++;
            break;
        case "down":
            position.y--;
            break;
        case "left":
            position.x--;
            break;
        case "right":
            position.x++;
            break;
        default:
            Error('Unknown Direction');
    }
};
/**
 * Let's make a game 🕹
 */
console.log(position); // { x: 0, y: 0}
move('up');
console.log(position); // { x: 0, y: 1}
move('down');
console.log(position); // { x: 0, y: 0}
move('left');
console.log(position); // { x: -1, y: 0}
move('right');
console.log(position); // { x: 0, y: 0}

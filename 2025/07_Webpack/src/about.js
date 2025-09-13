import hello_word from "./hello.js";
import world_word from "./world.js";
import css from "./style.css";

document.getElementById('root').innerHTML = world_word + ' ' + hello_word;
console.log('style css', css);
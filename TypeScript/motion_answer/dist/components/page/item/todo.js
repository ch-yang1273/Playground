import { BaseComponent } from '../../component.js';
export class TodoComponent extends BaseComponent {
    constructor(title, todo) {
        const htmlString = `<section class="todo">
  <h2 class="todo__title"></h2>
  <input type="checkbox" class="todo-checkbox">
</section>`;
        super(htmlString);
        const titleElement = this.element.querySelector('.todo__title');
        titleElement.textContent = title;
        const todoElement = this.element.querySelector('.todo-checkbox');
        todoElement.insertAdjacentText('afterend', todo);
    }
}

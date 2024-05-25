import { BaseComponent } from '../../component.js';

export class TodoComponent extends BaseComponent<HTMLElement> {
  constructor(title: string, todo: string) {
    const htmlString: string = `<section class="todo">
  <h2 class="todo__title"></h2>
  <input type="checkbox" class="todo-checkbox">
</section>`;
    super(htmlString);

    const titleElement: HTMLHeadingElement = this.element.querySelector('.todo__title')! as HTMLHeadingElement;
    titleElement.textContent = title;

    const todoElement: HTMLInputElement = this.element.querySelector('.todo-checkbox')! as HTMLInputElement;
    todoElement.insertAdjacentText('afterend', todo);
  }
}

import { BaseComponent } from '../../component.js';
export class NoteComponent extends BaseComponent {
    constructor(title, body) {
        const htmlString = `<section class="note">
  <h2 class="note__title"></h2>
  <p class="note__body"></p>
</section>`;
        super(htmlString);
        const titleElement = this.element.querySelector('.note__title');
        titleElement.textContent = title;
        const bodyElement = this.element.querySelector('.note__body');
        bodyElement.textContent = body;
    }
}

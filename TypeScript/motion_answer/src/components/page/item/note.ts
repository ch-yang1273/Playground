import { BaseComponent } from '../../component.js';

export class NoteComponent extends BaseComponent<HTMLElement> {
  constructor(title: string, body: string) {
    const htmlString: string = `<section class="note">
  <h2 class="note__title"></h2>
  <p class="note__body"></p>
</section>`;

    super(htmlString);

    const titleElement: HTMLHeadingElement = this.element.querySelector('.note__title')! as HTMLHeadingElement;
    titleElement.textContent = title;
    const bodyElement = this.element.querySelector('.note__body')! as HTMLParagraphElement;
    bodyElement.textContent = body;
  }
}

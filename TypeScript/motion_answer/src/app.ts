import { Composable, PageComponent } from './components/page/page.js';
import { ImageComponent } from './components/page/item/image.js';
import { NoteComponent } from './components/page/item/note.js';
import { TodoComponent } from './components/page/item/todo.js';
import { VideoComponent } from './components/page/item/video.js';
import { Component } from './components/component.js';

class App {
  private readonly page: Component & Composable;
  constructor(appRoot: HTMLElement) {
    this.page = new PageComponent();
    this.page.attachTo(appRoot);

    const image = new ImageComponent('Image Title', 'https://picsum.photos/600/300');
    this.page.addChild(image);

    const video: VideoComponent = new VideoComponent('Video Title', 'https://www.youtube.com/watch?v=LwG1g5kFIWM');
    this.page.addChild(video);

    const note: NoteComponent = new NoteComponent('Note Title', 'Note Content');
    this.page.addChild(note);

    const todo: TodoComponent = new TodoComponent('Todo Tile', 'Todo Item');
    this.page.addChild(todo);
  }
}

new App(document.querySelector('.document')! as HTMLElement);

import { PageComponent } from './components/page/page.js';
import { ImageComponent } from './components/page/item/image.js';
import { NoteComponent } from './components/page/item/note.js';
import { TodoComponent } from './components/page/item/todo.js';
import { VideoComponent } from './components/page/item/video.js';
class App {
    constructor(appRoot) {
        this.page = new PageComponent();
        this.page.attachTo(appRoot);
        const image = new ImageComponent('Image Title', 'https://picsum.photos/600/300');
        image.attachTo(appRoot, 'beforeend');
        const video = new VideoComponent('Video Title', 'https://www.youtube.com/watch?v=LwG1g5kFIWM');
        video.attachTo(appRoot, 'beforeend');
        const note = new NoteComponent('Note Title', 'Note Content');
        note.attachTo(appRoot, 'beforeend');
        const todo = new TodoComponent('Todo Tile', 'Todo Item');
        todo.attachTo(appRoot, 'beforeend');
    }
}
new App(document.querySelector('.document'));

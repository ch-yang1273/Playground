import { BaseComponent } from '../../component.js';
export class VideoComponent extends BaseComponent {
    constructor(title, url) {
        const htmlString = `<section class="video">
  <div class="video__player">
    <iframe width="990" height="566" frameborder="0"
    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
    referrerpolicy="strict-origin-when-cross-origin"
    allowfullscreen class="video__iframe"></iframe>
  </div>
  <h3 class="video__title"></h3>
</section>`;
        super(htmlString);
        console.log(url);
        const iframe = this.element.querySelector('.video__iframe');
        iframe.src = this.convertToEmbeddedURL(url);
        iframe.width = '400';
        iframe.height = '200';
        const titleElement = this.element.querySelector('.video__title');
        titleElement.textContent = title;
    }
    convertToEmbeddedURL(url) {
        const regExp = /^(?:https?:\/\/)?(?:www\.)?(?:(?:youtube.com\/(?:(?:watch\?v=)|(?:embed\/))([a-zA-Z0-9-]{11}))|(?:youtu.be\/([a-zA-Z0-9-]{11})))/;
        const match = url.match(regExp);
        const videoId = match ? match[1] || match[2] : undefined;
        if (videoId) {
            return `https://www.youtube.com/embed/${videoId}`;
        }
        return url;
    }
}

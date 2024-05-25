import { BaseComponent } from '../../component.js';

export class VideoComponent extends BaseComponent<HTMLElement> {
  constructor(title: string, url: string) {
    const htmlString: string = `<section class="video">
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
    const iframe: HTMLIFrameElement = this.element.querySelector('.video__iframe')! as HTMLIFrameElement;
    // URL을 Embedded 용 URL로 Covert
    iframe.src = this.convertToEmbeddedURL(url);
    iframe.width = '400';
    iframe.height = '200';

    const titleElement: HTMLParagraphElement = this.element.querySelector('.video__title')! as HTMLParagraphElement;
    titleElement.textContent = title;
  }

  // input
  // https://www.youtube.com/watch?v=K3-jG52XwuQ
  // https://youtu.be/K3-jG52XwuQ
  // output
  // https://www.youtube.com/embed/K3-jG52XwuQ
  // 정규표현식 Regex
  // https://regexr.com/5l6nr
  private convertToEmbeddedURL(url: string): string {
    // 정규 표현식 Regex
    const regExp =
      /^(?:https?:\/\/)?(?:www\.)?(?:(?:youtube.com\/(?:(?:watch\?v=)|(?:embed\/))([a-zA-Z0-9-]{11}))|(?:youtu.be\/([a-zA-Z0-9-]{11})))/;
    const match = url.match(regExp);
    const videoId = match ? match[1] || match[2] : undefined;
    if (videoId) {
      return `https://www.youtube.com/embed/${videoId}`;
    }
    return url;
  }
}

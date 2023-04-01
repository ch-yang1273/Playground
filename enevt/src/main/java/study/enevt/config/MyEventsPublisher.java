package study.enevt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyEventsPublisher {

    private final ApplicationEventPublisher publisher;

    public MyEventsPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void raise(Object event) {
        log.info("MyEventsPublisher.raise");
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }
}

package study.enevt.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCanceledEventHandler {

    @Async
    @EventListener(OrderCanceledEvent.class)
    public void handle(OrderCanceledEvent event) {
        log.info("Event OrderId = {}", event.getOrderId());
        log.info("Event CanceledTime = {}", event.getCanceledTime());
    }
}

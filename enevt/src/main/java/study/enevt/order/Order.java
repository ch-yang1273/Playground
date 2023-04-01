package study.enevt.order;

import lombok.extern.slf4j.Slf4j;
import study.enevt.config.MyEventsPublisher;

import java.time.LocalDateTime;

@Slf4j
public class Order {

    private Long id;
    private OrderStatus status;

    public Order(Long id) {
        this.id = id;
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel(LocalDateTime now, MyEventsPublisher publisher) {
        log.info("Order.cancel start");

        // 동기 방식이라면 Cancel Start -> EventListener -> Cancel end
        this.status = OrderStatus.CANCELED;
        publisher.raise(new OrderCanceledEvent(id, now));

        log.info("Order.cancel end");
    }
}

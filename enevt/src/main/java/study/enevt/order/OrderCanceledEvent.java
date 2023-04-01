package study.enevt.order;

import java.time.LocalDateTime;

public class OrderCanceledEvent {

    private final Long orderId;
    private final LocalDateTime canceledTime;

    public OrderCanceledEvent(Long orderId, LocalDateTime canceledTime) {
        this.orderId = orderId;
        this.canceledTime = canceledTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getCanceledTime() {
        return canceledTime;
    }
}

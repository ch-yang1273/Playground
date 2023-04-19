package study.enevt.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.enevt.config.MyEventsPublisher;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {

    public void cancelOrder(Long orderId) {
        Order order = new Order(orderId);
        order.cancel(LocalDateTime.now());
    }
}

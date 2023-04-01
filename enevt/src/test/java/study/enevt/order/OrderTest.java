package study.enevt.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import study.enevt.config.MyEventsPublisher;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RecordApplicationEvents
@SpringBootTest
class OrderTest {

    @MockBean
    private OrderCanceledEventHandler handler;

    @Autowired
    private MyEventsPublisher publisher;

    @Autowired
    private ApplicationEvents events;

    @Test
    void cancelOrder() {
        Order order = new Order(1L);
        order.cancel(LocalDateTime.now(), publisher);

        // 1. Mockito로 EventListener 가 호출되었는지 확인
        verify(handler, times(1)).handle(any(OrderCanceledEvent.class));

        // 2. @RecordApplicationEvents 발생된 OrderCanceledEvent 횟수를 확인
        int count = (int) events.stream(OrderCanceledEvent.class).count();
        assertThat(count).isEqualTo(1);
    }
}
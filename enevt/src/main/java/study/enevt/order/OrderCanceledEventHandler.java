package study.enevt.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class OrderCanceledEventHandler {

    @Async
    @EventListener
    public void handle1(OrderCanceledEvent event) {
        log.info("1. Event OrderId = {}", event.getOrderId());
        log.info("1. Event CanceledTime = {}", event.getCanceledTime());
    }

//    implementation 'org.springframework.boot:spring-boot-starter-jdbc' 추가 for spring-tx 라이브러리
    @TransactionalEventListener
    public void handle2(OrderCanceledEvent event) {
        log.info("2. Event OrderId = {}", event.getOrderId());
        log.info("2. Event CanceledTime = {}", event.getCanceledTime());
    }
}

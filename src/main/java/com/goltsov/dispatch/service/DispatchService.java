package com.goltsov.dispatch.service;

import com.goltsov.dispatch.message.DispatchCompleted;
import com.goltsov.dispatch.message.DispatchPreparing;
import com.goltsov.dispatch.message.OrderCreated;
import com.goltsov.dispatch.message.OrderDispatched;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DispatchService {

    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";

    private static final UUID APPLICATION_ID = UUID.randomUUID();

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(String key, OrderCreated orderCreated) throws Exception {

        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, key, dispatchPreparing).get();

        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .processedBy(APPLICATION_ID)
                .notes("Dispatched: " + orderCreated.getItem())
                .build();
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, key, orderDispatched).get();

        DispatchCompleted dispatchCompleted = DispatchCompleted.builder()
                .orderId(orderDispatched.getOrderId())
                .date(LocalDate.now().toString())
                .build();

        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, key, dispatchCompleted).get();

        log.info("Sent messages: key: " + key + ", orderId: "
                + orderCreated.getOrderId() + " processedById: " + APPLICATION_ID);
    }
}

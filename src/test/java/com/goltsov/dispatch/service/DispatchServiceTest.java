package com.goltsov.dispatch.service;

import com.goltsov.dispatch.message.OrderCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.goltsov.dispatch.util.TestEventData.buildOrderCreatedEvent;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class DispatchServiceTest {

    private DispatchService service;



    @BeforeEach
    void setUp() {
        service = new DispatchService();
    }

    @Test
    void process() {
        OrderCreated testEvent = buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        service.process(testEvent);
    }
}
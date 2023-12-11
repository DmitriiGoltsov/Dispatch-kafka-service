package com.goltsov.dispatch.handler;

import com.goltsov.dispatch.message.OrderCreated;
import com.goltsov.dispatch.service.DispatchService;

import com.goltsov.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static java.util.UUID.randomUUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler handler;

    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock = mock(DispatchService.class);
        handler = new OrderCreatedHandler(dispatchServiceMock);
    }

    @Test
    void listen() {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        handler.listen(testEvent);
        Mockito.verify(dispatchServiceMock, times(1)).process(testEvent);
    }
}
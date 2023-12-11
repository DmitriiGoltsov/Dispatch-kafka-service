package com.goltsov.dispatch.handler;

import com.goltsov.dispatch.service.DispatchService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        handler.listen("payload");
        Mockito.verify(dispatchServiceMock, times(1)).process("payload");
    }
}
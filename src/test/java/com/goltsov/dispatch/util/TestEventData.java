package com.goltsov.dispatch.util;

import com.goltsov.dispatch.message.OrderCreated;

import java.util.UUID;

public class TestEventData {

    public static OrderCreated buildOrderCreatedEvent(UUID orderId, String item) {
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}

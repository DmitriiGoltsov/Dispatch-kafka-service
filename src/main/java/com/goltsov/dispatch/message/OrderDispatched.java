package com.goltsov.dispatch.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDispatched {

    private UUID orderId;

    private UUID processedBy;

    private String notes;

}

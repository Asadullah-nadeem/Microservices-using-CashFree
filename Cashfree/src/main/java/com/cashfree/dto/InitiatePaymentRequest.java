package com.cashfree.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InitiatePaymentRequest {
    private BigDecimal amount;
    private String userId;
    private String orderId;
}

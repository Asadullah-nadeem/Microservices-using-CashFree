package com.Payment.Payment.dto;

import lombok.Data;

@Data
public class InitiatePaymentResponse {
    private String paymentId;
    private String paymentUrl;
}
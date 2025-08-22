package com.cashfree.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitiatePaymentResponse {

    private String paymentId;
    private String paymentUrl;
}

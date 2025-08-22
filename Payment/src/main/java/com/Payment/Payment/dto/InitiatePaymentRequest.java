package com.Payment.Payment.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitiatePaymentRequest {
    private BigDecimal amount;
    private String userId;
    private String orderId;
}

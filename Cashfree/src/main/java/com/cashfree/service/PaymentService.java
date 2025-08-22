package com.cashfree.service;

import com.cashfree.dto.InitiatePaymentRequest;
import com.cashfree.dto.InitiatePaymentResponse;
import com.cashfree.dto.PaymentStatusResponse;


public interface PaymentService {
    InitiatePaymentResponse initiatePayment(InitiatePaymentRequest request);
    void handleCallback(Object cashfreeCallbackPayload);
    PaymentStatusResponse getPaymentStatus(String paymentId);
}
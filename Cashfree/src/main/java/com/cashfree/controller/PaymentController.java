package com.cashfree.controller;

import com.cashfree.dto.InitiatePaymentRequest;
import com.cashfree.dto.InitiatePaymentResponse;
import com.cashfree.dto.PaymentStatusResponse;
import com.cashfree.service.PaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<InitiatePaymentResponse> initiatePayment(@RequestBody InitiatePaymentRequest request) {
        InitiatePaymentResponse response = paymentService.initiatePayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/callback")
    public ResponseEntity<Map<String, String>> handleCallback(@RequestBody Object payload) {
        // Cashfree expects a 200 OK response to acknowledge receipt of the callback.
        paymentService.handleCallback(payload);
        return ResponseEntity.ok(Map.of("status", "SUCCESS"));
    }

    @GetMapping("/status/{paymentId}")
    public ResponseEntity<PaymentStatusResponse> getPaymentStatus(@PathVariable String paymentId) {
        PaymentStatusResponse response = paymentService.getPaymentStatus(paymentId);
        return ResponseEntity.ok(response);
    }
}
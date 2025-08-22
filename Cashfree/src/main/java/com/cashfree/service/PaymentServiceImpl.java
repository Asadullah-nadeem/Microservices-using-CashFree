package com.cashfree.service;

import com.cashfree.Repository.PaymentRepository;
import com.cashfree.dto.InitiatePaymentRequest;
import com.cashfree.dto.InitiatePaymentResponse;
import com.cashfree.dto.PaymentStatusResponse;
import com.cashfree.model.Payment;
import com.cashfree.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public InitiatePaymentResponse initiatePayment(InitiatePaymentRequest request) {
        //        unique payment Id
        String paymentId = UUID
                .randomUUID()
                .toString();
        //        TODO: Call CashFree API To Get the actual payment url
        //        we'll use a Placeholder URL()
        String paymentUrlFromCashfree = "https://upi.cashfree.com/pay?id=" + paymentId;

        //       Create and save the payment entity to the database
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        // CORRECTED: Use the getter for orderId
        payment.setOrderId(request.getOrderId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        //        Initial Status setStatus - PENDING, SUCCESS, FAILURE
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentUrl(paymentUrlFromCashfree);
        paymentRepository.save(payment);

        // CORRECTED: Return the correct response object (InitiatePaymentResponse)
        return new InitiatePaymentResponse(paymentId, paymentUrlFromCashfree);

    }

    @Override
    public void handleCallback(Object cashfreeCallbackPayload) {
        // TODO:
        // 1. Parse the callback payload from Cashfree
        // 2. Extract the paymentId and the final status (SUCCESS/FAILURE)
        // 3. Find the payment in your database using the paymentId
        // 4. Update its status accordingly
        // 5. Save the updated payment entity
        System.out.println("Received callback: " + cashfreeCallbackPayload.toString());
    }

    @Override
    public PaymentStatusResponse getPaymentStatus(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId)); // Replace with a proper exception

        return new PaymentStatusResponse(payment.getStatus().toString(), "Payment status retrieved successfully.");
    }
}

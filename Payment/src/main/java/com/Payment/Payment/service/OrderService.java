package com.Payment.Payment.service;
import com.Payment.Payment.dto.CreateOrderRequest;
import com.Payment.Payment.dto.CreateOrderResponse;
import com.Payment.Payment.dto.InitiatePaymentRequest;
import com.Payment.Payment.dto.InitiatePaymentResponse;
import com.Payment.Payment.model.Order;
import com.Payment.Payment.model.OrderStatus;

import com.Payment.Payment.repository.OrderRepository;
import com.Payment.Payment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        // 1. In a real app, you'd calculate this from the user's cart.
        // For this example, we'll use a dummy amount.
        BigDecimal dummyAmount = new BigDecimal("150.75");

        // 2. Create and save the order with PENDING status
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserId(request.getUserId());
        order.setTotalAmount(dummyAmount);
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        // 3. Prepare the request for the Payment Service
        InitiatePaymentRequest paymentReq = new InitiatePaymentRequest(
                order.getTotalAmount(),
                order.getUserId(),
                order.getOrderId()
        );

        // 4. Call the Payment Service via the API Gateway
        // The URL uses the service name registered with Eureka (PAYMENT-SERVICE)
        String paymentServiceUrl = "http://API-GATEWAY/api/payments/initiate";
        InitiatePaymentResponse paymentResponse = restTemplate.postForObject(
                paymentServiceUrl,
                paymentReq,
                InitiatePaymentResponse.class
        );

        if (paymentResponse == null || paymentResponse.getPaymentUrl() == null) {
            throw new RuntimeException("Failed to initiate payment");
        }

        // 5. Return the Order ID and the Payment URL from the Payment Service
        return new CreateOrderResponse(order.getOrderId(), paymentResponse.getPaymentUrl());
    }
}


package com.shopApplication.PaymentService.service;

import com.shopApplication.PaymentService.model.PaymentRequest;
import com.shopApplication.PaymentService.model.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}

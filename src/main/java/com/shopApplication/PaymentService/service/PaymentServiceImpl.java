package com.shopApplication.PaymentService.service;

import com.shopApplication.PaymentService.entity.PaymentMode;
import com.shopApplication.PaymentService.entity.TransactionDetails;
import com.shopApplication.PaymentService.exception.CustomPaymentException;
import com.shopApplication.PaymentService.model.PaymentRequest;
import com.shopApplication.PaymentService.model.PaymentResponse;
import com.shopApplication.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Log4j2
public class PaymentServiceImpl implements  PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: "+paymentRequest);
        TransactionDetails transactionDetails= TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction completed with id: "+transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("Getting payment details for the Order Id:{}"+orderId);
        TransactionDetails details=transactionDetailsRepository.findByOrderId(orderId).orElseThrow(()->
                new CustomPaymentException("Payment Details not found with id:{}"+orderId,"PAYMENT_DETAILS_NOT_FOUND",404));
        return PaymentResponse.builder()
                .paymentId(details.getId())
                .paymentMode(PaymentMode.valueOf(details.getPaymentMode()))
                .amount(details.getAmount())
                .paymentDate(details.getPaymentDate())
                .status(details.getPaymentStatus())
                .orderId(details.getOrderId())
                .build();
    }
}

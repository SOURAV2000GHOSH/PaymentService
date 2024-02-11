package com.shopApplication.PaymentService.exception;

import lombok.Data;

@Data
public class CustomPaymentException extends RuntimeException{
    private String status;
    private int errorCode;
    public CustomPaymentException(String message,String status,int errorCode){
        super(message);
        this.status=status;
        this.errorCode=errorCode;
    }
}

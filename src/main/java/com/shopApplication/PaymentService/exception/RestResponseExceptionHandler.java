package com.shopApplication.PaymentService.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomPaymentException.class)
    public ResponseEntity<ErrorResponse> handlePaymentServiceException(CustomPaymentException exception){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .errorCode(exception.getErrorCode())
                        .build(), HttpStatusCode.valueOf(exception.getErrorCode())
        );
    }
}

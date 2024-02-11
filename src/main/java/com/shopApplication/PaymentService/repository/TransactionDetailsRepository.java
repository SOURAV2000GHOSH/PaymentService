package com.shopApplication.PaymentService.repository;

import com.shopApplication.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {

    public Optional<TransactionDetails> findByOrderId(long orderId);
}

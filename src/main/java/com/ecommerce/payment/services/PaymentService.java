package com.ecommerce.payment.services;

import com.ecommerce.payment.dtos.PaymentDto;

public interface PaymentService {
    void save(PaymentDto paymentDto);
}

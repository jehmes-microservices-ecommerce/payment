package com.ecommerce.payment.services;

import com.ecommerce.payment.dtos.PaymentDto;
import com.ecommerce.payment.models.Payment;

public interface PaymentService {
    Payment save(Payment payment);
}

package com.ecommerce.payment.services;

import com.ecommerce.payment.dtos.OrderDto;
import com.ecommerce.payment.dtos.PaymentDto;
import com.ecommerce.payment.models.Payment;

public interface PaymentService {
    void saveAndPublish(OrderDto orderDto);
}

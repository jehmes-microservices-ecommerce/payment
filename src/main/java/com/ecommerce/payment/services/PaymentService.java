package com.ecommerce.payment.services;

import com.ecommerce.payment.dtos.OrderDto;

public interface PaymentService {
    void saveAndPublish(OrderDto orderDto);
}

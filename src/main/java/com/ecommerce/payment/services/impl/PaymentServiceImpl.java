package com.ecommerce.payment.services.impl;

import com.ecommerce.payment.dtos.PaymentDto;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.repositories.PaymentRepository;
import com.ecommerce.payment.services.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(PaymentDto paymentDto) {
        var payment = new Payment();
        BeanUtils.copyProperties(paymentDto, payment);
        paymentRepository.save(payment);
    }
}

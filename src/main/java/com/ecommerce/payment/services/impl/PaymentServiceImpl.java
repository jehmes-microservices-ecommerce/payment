package com.ecommerce.payment.services.impl;

import com.ecommerce.payment.dtos.OrderDto;
import com.ecommerce.payment.dtos.PaymentDto;
import com.ecommerce.payment.enums.PaymentStatus;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.publishers.OrderPublisher;
import com.ecommerce.payment.repositories.PaymentRepository;
import com.ecommerce.payment.services.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final OrderPublisher orderPublisher;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(OrderPublisher orderPublisher, PaymentRepository paymentRepository) {
        this.orderPublisher = orderPublisher;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void saveAndPublish(OrderDto orderDto) {
        var payment = new Payment();
        BeanUtils.copyProperties(orderDto.getPaymentDto(), payment);
        payment.setUser(orderDto.getUser().convertToUser());
        payment.setLastDigitsCreditCard(orderDto.getPaymentDto().getCardNumbers().substring(payment.getCardNumbers().length()-4));
        payment = paymentRepository.save(payment);
        orderDto.getPaymentDto().setPaymentId(payment.getPaymentId());
        orderDto.setPaymentStatus(PaymentStatus.APPROVED);
        orderPublisher.publish(orderDto);
    }
}

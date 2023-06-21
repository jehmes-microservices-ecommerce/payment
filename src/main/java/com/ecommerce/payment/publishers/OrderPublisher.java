package com.ecommerce.payment.publishers;

import com.ecommerce.payment.dtos.OrderDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Value(value = "${ecommerce.broker.exchange.orderPaymentCommand}")
    private String orderPaymentExchange;
    @Value(value = "${ecommerce.broker.key.bindOrderReplyCommand}")
    private String bindOrderPayment;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(OrderDto orderDto) {
        rabbitTemplate.convertAndSend(orderPaymentExchange, bindOrderPayment, orderDto);
    }
}

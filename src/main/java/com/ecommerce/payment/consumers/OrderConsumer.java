package com.ecommerce.payment.consumers;

import com.ecommerce.payment.dtos.OrderDto;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.services.PaymentService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final PaymentService paymentService;

    public OrderConsumer(RabbitTemplate rabbitTemplate, PaymentService paymentService) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentService = paymentService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ecommerce.broker.queue.orderRequest}", durable = "true"),
            exchange = @Exchange(value = "${ecommerce.broker.exchange.orderEvent}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ecommerce.broker.key.bindOrderRequest}"
    ))
    public void listenOrderEvent(@Payload OrderDto orderDto) {
        paymentService.save(orderDto.getPaymentDto());
    }
}

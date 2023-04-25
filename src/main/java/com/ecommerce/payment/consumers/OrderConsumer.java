package com.ecommerce.payment.consumers;

import com.ecommerce.payment.dtos.OrderDto;
import com.ecommerce.payment.enums.PaymentStatus;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.publishers.OrderPublisher;
import com.ecommerce.payment.services.PaymentService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final PaymentService paymentService;
    private final OrderPublisher orderPublisher;

    public OrderConsumer(PaymentService paymentService, OrderPublisher orderPublisher) {
        this.paymentService = paymentService;
        this.orderPublisher = orderPublisher;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ecommerce.broker.queue.orderRequest}", durable = "true"),
            exchange = @Exchange(value = "${ecommerce.broker.exchange.orderEvent}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ecommerce.broker.key.bindOrderRequest}"
    ))
    public void listenOrderEvent(@Payload OrderDto orderDto) {
        Payment payment = paymentService.save(orderDto.getPaymentDto());
        orderDto.getPaymentDto().setPaymentId(payment.getPaymentId());
        orderDto.setPaymentStatus(PaymentStatus.APPROVED);
        orderPublisher.publish(orderDto);
    }
}

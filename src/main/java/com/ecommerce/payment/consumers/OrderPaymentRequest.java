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
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class OrderPaymentRequest {

    private final PaymentService paymentService;

    public OrderPaymentRequest(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ecommerce.broker.queue.orderPaymentRequest}", durable = "true"),
            exchange = @Exchange(value = "${ecommerce.broker.exchange.orderPaymentCommand}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ecommerce.broker.key.bindOrderRequestCommand}"
    ))
    public void listenOrderPaymentCommand(@Payload OrderDto orderDto) {
        orderDto.getPaymentDto().setRequestDateTime((LocalDateTime.now(ZoneId.of("UTC"))));
        paymentService.saveAndPublish(orderDto);
    }
}

package com.ecommerce.payment.models;

import com.ecommerce.payment.dtos.OrderDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_PAYMENT")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID paymentId;
    @Column(nullable = false)
    private String cardNumbers;
    @Column
    private LocalDateTime requestDateTime;
    @Column
    private String lastDigitsCreditCard;
    @Column
    private BigDecimal valuePaid;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Payment() {
    }

    public Payment(String cardNumber, LocalDateTime localDateTime) {
        this.cardNumbers = cardNumber;
        this.requestDateTime = localDateTime;
    }

    public static Payment convertToModel(OrderDto orderDto) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(orderDto.getPaymentDto(), payment);
        payment.setLastDigitsCreditCard(orderDto.getPaymentDto().getCardNumbers().substring(orderDto.getPaymentDto().getCardNumbers().length() - 4));
        payment.setUser(orderDto.getUser().convertToUser());
        return payment;
    }
}

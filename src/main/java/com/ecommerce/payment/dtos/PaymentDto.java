package com.ecommerce.payment.dtos;

import com.ecommerce.payment.models.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class PaymentDto {
    private UUID paymentId;
    private String cardNumbers;
    private LocalDateTime requestDateTime;
    private BigDecimal valuePaid;

    public PaymentDto() {
    }

    public PaymentDto(String cardNumber, LocalDateTime localDateTime, BigDecimal valuePaid) {
        this.cardNumbers = cardNumber;
        this.requestDateTime = localDateTime;
        this.valuePaid = valuePaid;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(String cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public BigDecimal getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(BigDecimal valuePaid) {
        this.valuePaid = valuePaid;
    }

}

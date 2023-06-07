package com.ecommerce.payment.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    private String cardNumber;
    @Column
    @CreationTimestamp
    private LocalDateTime requestDate;
    @Column
    private String lastDigitsCreditCard;
    @Column
    private String valuePaid;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Payment() {
    }

    public Payment(String cardNumber, LocalDateTime localDateTime) {
        this.cardNumber = cardNumber;
        this.requestDate = localDateTime;
    }

}

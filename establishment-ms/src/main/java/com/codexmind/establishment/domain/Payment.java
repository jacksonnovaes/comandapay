package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentWithCreditCard.class, name = "cartao"),
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Payment {

    @Id
    private Integer paymentId;

    private Integer paymentStatus;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_order")
    @MapsId
    private Order order;

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.toEnum(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCod();
    }
}

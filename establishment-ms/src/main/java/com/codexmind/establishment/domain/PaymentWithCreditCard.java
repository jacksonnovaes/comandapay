package com.codexmind.establishment.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonTypeName("cartao")
@NoArgsConstructor
public class PaymentWithCreditCard extends Payment {


    private Integer installments;

    public PaymentWithCreditCard(Long id, Integer paymentStatus, Order order, Integer installments) {
        this.installments = installments;
    }
}

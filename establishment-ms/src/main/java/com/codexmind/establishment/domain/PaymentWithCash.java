package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonTypeName("Dinheiro")
@NoArgsConstructor
public class PaymentWithCash extends Payment {

    private LocalDate instateConfirmation;

    private BigDecimal receivedValue;

    public PaymentWithCash(Integer id, PaymentStatus paymentStatus, Order order, LocalDate instateConfirmation, BigDecimal receivedValue) {
        super(id, paymentStatus, order);
        this.instateConfirmation = instateConfirmation;
        this.receivedValue = receivedValue;
    }
}

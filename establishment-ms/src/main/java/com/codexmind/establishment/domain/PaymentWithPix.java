package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonTypeName("PaymentWithPix")
@NoArgsConstructor
public class PaymentWithPix extends Payment {


    private LocalDate instateConfirmation;

    public PaymentWithPix(Integer id, PaymentStatus paymentStatus, Order order, LocalDate instateConfirmation) {
        super(id, paymentStatus, order);
        this.instateConfirmation = instateConfirmation;
    }
}

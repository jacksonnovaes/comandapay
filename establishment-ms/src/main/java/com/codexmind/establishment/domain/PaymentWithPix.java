package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonTypeName("PaymentWithPix")
public class PaymentWithPix extends Payment {


    private Date instateConfirmation;

    public PaymentWithPix(Integer id, PaymentStatus paymentStatus, Order order, Date instateConfirmation) {
        super(id, paymentStatus, order);
        this.instateConfirmation = instateConfirmation;
    }
}

package com.codexmind.establishment.usecases.payment;

import com.codexmind.establishment.dto.asaas.PaymentResponseDTO;
import com.codexmind.establishment.dto.asaas.RequestPixTransactonDTO;

public interface DoPaymentAsaas {

    PaymentResponseDTO pixPayment(RequestPixTransactonDTO transactionDTO);


}

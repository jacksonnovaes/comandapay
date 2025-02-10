package com.codexmind.establishment.usecases.payment;

import com.codexmind.establishment.dto.asaas.PaymentResponseDTO;
import com.codexmind.establishment.dto.asaas.QrCodeDTO;
import com.codexmind.establishment.dto.asaas.TransactonDTO;

public interface DoPaymentAsaas {

    PaymentResponseDTO pixPayment(TransactonDTO transactionDTO);

    QrCodeDTO getQrCode(String id);
}

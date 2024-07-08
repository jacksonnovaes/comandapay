package com.paymenthub.payauth.useCases;

import com.paymenthub.payauth.dto.PixTransactionDTO;
import com.paymenthub.payauth.service.EfiPixCob;
import org.springframework.stereotype.Service;

@Service
public class GetBillings {
    private final AuthEfiPay authEfiPay;

    private final EfiPixCob efiPixCob;

    public GetBillings(AuthEfiPay authEfiPay, EfiPixCob efiPixCob) {

        this.authEfiPay = authEfiPay;
        this.efiPixCob = efiPixCob;
    }

    public PixTransactionDTO execute(String txId) {
        var authorizing = authEfiPay.execute();
        return efiPixCob.getAllbillings(txId, authorizing.tokeType() + " "+ authorizing.acessToken());
    }
}

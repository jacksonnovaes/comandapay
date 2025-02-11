package com.paymenthub.payauth.useCases;

import org.springframework.stereotype.Service;

import com.paymenthub.payauth.dto.PixTransactionDTO;
import com.paymenthub.payauth.dto.TransactionDTO;
import com.paymenthub.payauth.service.EfiPixCob;

@Service
public class DoPayment {

  private final AuthEfiPay authEfiPay;

  private final EfiPixCob efiPixCob;

  public DoPayment(AuthEfiPay authEfiPay, EfiPixCob efiPixCob) {

    this.authEfiPay = authEfiPay;
    this.efiPixCob = efiPixCob;
}


    public PixTransactionDTO execute(TransactionDTO transactionDTO) {
       
        var authorizing = authEfiPay.execute();

        return efiPixCob.duePixCobv(transactionDTO, authorizing.tokeType() + " "+ authorizing.acessToken());
  }

}

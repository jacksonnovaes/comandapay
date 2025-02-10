package com.codexmind.establishment.usecases.payment;

import com.codexmind.establishment.domain.PixTransaction;
import com.codexmind.establishment.dto.TransactionDTO;

public interface DoPaymentEfi {

    PixTransaction pixPayment(TransactionDTO transactionDTO);


}

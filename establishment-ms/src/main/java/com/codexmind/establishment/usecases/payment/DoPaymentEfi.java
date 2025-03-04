package com.codexmind.establishment.usecases.payment;

import com.codexmind.establishment.domain.PixTransaction;
import com.codexmind.establishment.dto.EfiRequestDTO;

public interface DoPaymentEfi {

    PixTransaction pixPayment(EfiRequestDTO transactionDTO);


}

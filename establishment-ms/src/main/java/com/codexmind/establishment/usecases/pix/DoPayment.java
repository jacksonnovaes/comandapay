package com.codexmind.establishment.usecases.pix;

import java.util.HashMap;


import com.codexmind.establishment.dto.AuthorizationDTO;
import com.codexmind.establishment.service.EfiPayAuth;
import com.codexmind.establishment.service.EfiPixCob;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.Credentials;
import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import lombok.RequiredArgsConstructor;

@Service
public class DoPayment {


  private final Credentials credentials;

  private final EfiPayAuth efiPayAuth;

  private final EfiPixCob efiPixCob;

    public DoPayment(Credentials credentials, EfiPayAuth efiPayAuth, EfiPixCob efiPixCob) {
        this.credentials = credentials;
        this.efiPayAuth = efiPayAuth;
        this.efiPixCob = efiPixCob;
    }


    public PixTransactionDTO execute(TransactionDTO transactionDTO) {
        var pixTransactionDTO = efiPixCob.duePixCobv(transactionDTO);
        return pixTransactionDTO;
  }

}

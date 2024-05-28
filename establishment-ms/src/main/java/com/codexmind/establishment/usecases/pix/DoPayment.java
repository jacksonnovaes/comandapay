package com.codexmind.establishment.usecases.pix;

import java.util.HashMap;


import com.codexmind.establishment.domain.PixTransaction;
import com.codexmind.establishment.dto.AuthorizationDTO;
import com.codexmind.establishment.repository.PixTransactionRepository;
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



  private final EfiPixCob efiPixCob;

  private final PixTransactionRepository pixTransactionRepository;

    public DoPayment(EfiPixCob efiPixCob, PixTransactionRepository pixTransactionRepository) {

        this.efiPixCob = efiPixCob;
        this.pixTransactionRepository = pixTransactionRepository;
    }


    public PixTransactionDTO execute(TransactionDTO transactionDTO) {
        transactionDTO.setChave("9392925e-0257-417a-a180-506379529a2f");
        var pixTransactionDTO = efiPixCob.duePixCobv(transactionDTO);
        PixTransaction pixTransaction = new PixTransaction();
        pixTransaction.setExpiracao(pixTransactionDTO.calendario().expiracao());
        pixTransaction.setTxid(pixTransactionDTO.txid());
        pixTransaction.setRevisao(pixTransactionDTO.revisao());
        pixTransaction.setStatus(pixTransactionDTO.status());
        pixTransaction.setValor(pixTransactionDTO.valor().original());
        pixTransaction.setChave(pixTransaction.getChave());
        pixTransaction.setDevedor(pixTransactionDTO.devedor().nome());
        pixTransaction.setSolicitacaoPagador(pixTransactionDTO.solicitacaoPagador());
        pixTransaction.setLocation(pixTransactionDTO.location());
        pixTransaction.setPixCopiaECola(pixTransactionDTO.pixCopiaECola());
        return pixTransactionDTO;
  }

}

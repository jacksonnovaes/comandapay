package com.codexmind.establishment.usecases.pix;

import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.service.EfiPixCob;
import org.springframework.stereotype.Service;

@Service
public class GetBillings {

    private final EfiPixCob efiPixCob;

    public GetBillings(EfiPixCob efiPixCob) {
        this.efiPixCob = efiPixCob;
    }

    public PixTransactionDTO execute(String txid){
        return  efiPixCob.getBillings(txid);
    }
}

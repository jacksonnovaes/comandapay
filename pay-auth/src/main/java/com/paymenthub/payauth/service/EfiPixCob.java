package com.paymenthub.payauth.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.paymenthub.payauth.dto.PixTransactionDTO;
import com.paymenthub.payauth.dto.TransactionDTO;

@FeignClient(name = "efiCob", url = "https://pix-h.api.efipay.com.br")
public interface EfiPixCob {

    @PostMapping("/v2/cob")
    PixTransactionDTO duePixCobv(@RequestBody TransactionDTO transactionDTO, @RequestHeader("Authorization") String authorization);
}

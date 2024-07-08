package com.paymenthub.payauth.service;


import com.paymenthub.payauth.dto.CobTransactionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.paymenthub.payauth.dto.PixTransactionDTO;
import com.paymenthub.payauth.dto.TransactionDTO;

@FeignClient(name = "efiCob", url = "https://pix.api.efipay.com.br")
public interface EfiPixCob {

    @PostMapping("/v2/cob")
    PixTransactionDTO duePixCobv(@RequestBody TransactionDTO transactionDTO, @RequestHeader("Authorization") String authorization);

    @GetMapping("/v2/cob/{txid}")
    PixTransactionDTO getAllbillings(@PathVariable String txid, @RequestHeader("Authorization") String authorization);

}

package com.codexmind.establishment.service;

import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "efiCob", url = "${efi.cob.url}")
public interface EfiPixCob {

    @PostMapping("/v2/cob/pix")
    PixTransactionDTO duePixCobv(@RequestBody TransactionDTO transactionDTO);

    @GetMapping("/v2/cob/{txid}")
    PixTransactionDTO getBillings(@PathVariable String txid);
}

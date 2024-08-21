package com.codexmind.establishment.service;

import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qrCode", url = "https://pix.api.efipay.com.br")
public interface EfiPixQrCode {

    @GetMapping("/v2/loc/{id}/qrcode")
    String getQrCode(@PathVariable String id);
}

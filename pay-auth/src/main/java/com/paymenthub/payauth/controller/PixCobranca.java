package com.paymenthub.payauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymenthub.payauth.dto.PixTransactionDTO;
import com.paymenthub.payauth.dto.TransactionDTO;
import com.paymenthub.payauth.useCases.DoPayment;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/v2/cob")
public class PixCobranca {


    private final DoPayment doPayment;

    public PixCobranca(DoPayment doPayment){
        this.doPayment = doPayment;
    }
    @PostMapping("/pix")   
    public ResponseEntity<PixTransactionDTO> pix(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok(doPayment.execute(transactionDTO));

    }
}

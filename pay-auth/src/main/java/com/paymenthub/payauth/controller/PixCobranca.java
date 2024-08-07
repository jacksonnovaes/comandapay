package com.paymenthub.payauth.controller;

import com.paymenthub.payauth.useCases.GetBillings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paymenthub.payauth.dto.PixTransactionDTO;
import com.paymenthub.payauth.dto.TransactionDTO;
import com.paymenthub.payauth.useCases.DoPayment;


@RestController
@RequestMapping("/v2/cob")
public class PixCobranca {


    private final DoPayment doPayment;

    private final GetBillings getBillings;

    public PixCobranca(DoPayment doPayment, GetBillings getBillings){
        this.doPayment = doPayment;
        this.getBillings = getBillings;
    }
    @PostMapping("/pix")   
    public ResponseEntity<PixTransactionDTO> pix(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok(doPayment.execute(transactionDTO));
    }

    @GetMapping("/{txid}")
    public ResponseEntity<PixTransactionDTO> getBilling(@PathVariable String txid){
        return ResponseEntity.ok(getBillings.execute(txid));
    }
}

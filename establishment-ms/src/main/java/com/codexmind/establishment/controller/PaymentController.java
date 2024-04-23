package com.codexmind.establishment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;
import com.codexmind.establishment.usecases.pix.DoPayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {



    private final DoPayment doPayment;

    public PaymentController(DoPayment doPayment) {
        this.doPayment = doPayment;
    }


    @PostMapping("/pix")
    public ResponseEntity<PixTransactionDTO> postMethodName(@RequestBody TransactionDTO transactionDTO) throws IOException {
        var pixTransactionDTO = doPayment.execute(transactionDTO); 
        return ResponseEntity.ok(pixTransactionDTO);
    }
    
}

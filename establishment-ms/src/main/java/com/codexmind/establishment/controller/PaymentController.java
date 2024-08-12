package com.codexmind.establishment.controller;

import com.codexmind.establishment.converters.TransactionConverter;
import com.codexmind.establishment.service.EfiPixQrCode;
import org.springframework.web.bind.annotation.*;

import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;
import com.codexmind.establishment.usecases.pix.DoPayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {



    private final DoPayment doPayment;

    private final EfiPixQrCode efiPixQrCode;

    public PaymentController(DoPayment doPayment, EfiPixQrCode efiPixQrCode) {
        this.doPayment = doPayment;
        this.efiPixQrCode = efiPixQrCode;
    }


    @PostMapping("/pix")
    public ResponseEntity<PixTransactionDTO> postMethodName(@RequestBody TransactionDTO transactionDTO) throws IOException {

        var pixTransactionDTO = doPayment.execute(transactionDTO);
        return ResponseEntity.ok(TransactionConverter.toDTO(pixTransactionDTO));
    }

    @GetMapping("/loc/{id}/qrcode")
    public String getQrCode(@PathVariable String id){
        return efiPixQrCode.getQrCode(id);
    }
    
}

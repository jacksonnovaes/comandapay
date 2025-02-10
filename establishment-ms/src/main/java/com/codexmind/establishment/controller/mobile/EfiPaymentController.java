package com.codexmind.establishment.controller.mobile;

import com.codexmind.establishment.converters.TransactionConverter;
import com.codexmind.establishment.service.EFI.EfiPixQrCode;
import org.springframework.web.bind.annotation.*;

import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.EfiRequestDTO;
import com.codexmind.establishment.usecases.payment.impl.DoPaymentEfiImpl;

import org.springframework.http.ResponseEntity;

import java.io.IOException;


@RestController
@RequestMapping("/efi/v1/payment")
public class EfiPaymentController {


    private final DoPaymentEfiImpl doPayment;

    private final EfiPixQrCode efiPixQrCode;

    public EfiPaymentController(DoPaymentEfiImpl doPayment, EfiPixQrCode efiPixQrCode) {
        this.doPayment = doPayment;
        this.efiPixQrCode = efiPixQrCode;
    }


    @PostMapping("/pix")
    public ResponseEntity<PixTransactionDTO> postMethodName(@RequestBody EfiRequestDTO transactionDTO) throws IOException {

        var pixTransactionDTO = doPayment.pixPayment(transactionDTO);
        return ResponseEntity.ok(TransactionConverter.toDTO(pixTransactionDTO));
    }

    @GetMapping("/loc/{id}/qrcode")
    public String getQrCode(@PathVariable String id) {
        return efiPixQrCode.getQrCode(id);
    }

}

package com.codexmind.establishment.controller.mobile;

import com.codexmind.establishment.converters.TransactionConverter;
import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;
import com.codexmind.establishment.dto.asaas.PaymentResponseDTO;
import com.codexmind.establishment.dto.asaas.RequestPixTransactonDTO;
import com.codexmind.establishment.service.EFI.EfiPixQrCode;
import com.codexmind.establishment.usecases.payment.DoPaymentAsaas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/asaas/v1/payment")
public class AsaasPaymentController {


    private final DoPaymentAsaas doPayment;


    public AsaasPaymentController(DoPaymentAsaas doPayment, EfiPixQrCode efiPixQrCode) {
        this.doPayment = doPayment;
    }


    @PostMapping("/pix")
    public ResponseEntity<PaymentResponseDTO> postMethodName(@RequestBody RequestPixTransactonDTO transactionDTO) throws IOException {

        var pixTransactionDTO = doPayment.pixPayment(transactionDTO);
        return ResponseEntity.ok(pixTransactionDTO);
    }

}

package com.codexmind.establishment.controller.mobile;

import com.codexmind.establishment.dto.asaas.PaymentResponseDTO;
import com.codexmind.establishment.dto.asaas.QrCodeDTO;
import com.codexmind.establishment.dto.asaas.TransactonDTO;
import com.codexmind.establishment.service.EFI.EfiPixQrCode;
import com.codexmind.establishment.usecases.payment.DoPaymentAsaas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class PaymentController {


    private final DoPaymentAsaas doPayment;


    public PaymentController(DoPaymentAsaas doPayment, EfiPixQrCode efiPixQrCode) {
        this.doPayment = doPayment;
    }


    @PostMapping("/pix")
    public ResponseEntity<PaymentResponseDTO> postMethodName(@RequestBody TransactonDTO transactionDTO) throws IOException {

        var pixTransactionDTO = doPayment.pixPayment(transactionDTO);
        return ResponseEntity.ok(pixTransactionDTO);
    }

    @GetMapping("/qrCode/{id}")
    public ResponseEntity<QrCodeDTO> getQrCode(@PathVariable String id){
        return ResponseEntity.ok(doPayment.getQrCode(id));

    }

}

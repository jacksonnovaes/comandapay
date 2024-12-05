package com.codexmind.establishment.controller.mobile;


import com.codexmind.establishment.converters.TransactionConverter;
import com.codexmind.establishment.dto.PixTransactionDTO;
import com.codexmind.establishment.dto.TransactionDTO;
import com.codexmind.establishment.usecases.pix.DoPaymentEfiImpl;
import com.codexmind.establishment.usecases.pix.GetBillings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v2/cob")
public class PixCobranca {


    private final DoPaymentEfiImpl doPayment;

    private final GetBillings getBillings;

    public PixCobranca(DoPaymentEfiImpl doPayment, GetBillings getBillings) {
        this.doPayment = doPayment;
        this.getBillings = getBillings;
    }

    @PostMapping("/pix")
    public ResponseEntity<PixTransactionDTO> pix(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(TransactionConverter.toDTO(doPayment.pixPayment(transactionDTO)));

    }

    @GetMapping("/pix/{txid}")
    public ResponseEntity<PixTransactionDTO> pix(@PathVariable String txid) {
        return ResponseEntity.ok(getBillings.execute(txid));

    }
}

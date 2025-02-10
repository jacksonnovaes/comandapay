package com.codexmind.establishment.usecases.payment.impl;

import com.codexmind.establishment.usecases.payment.DoPaymentEfi;
import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.PixTransaction;
import com.codexmind.establishment.dto.TransactionDTO;
import com.codexmind.establishment.repository.PixTransactionRepository;
import com.codexmind.establishment.service.EFI.EfiPixCob;

@Service
public class DoPaymentEfiImpl implements DoPaymentEfi {

    private final EfiPixCob efiPixCob;

    private final PixTransactionRepository pixTransactionRepository;

    public DoPaymentEfiImpl(EfiPixCob efiPixCob, PixTransactionRepository pixTransactionRepository) {

        this.efiPixCob = efiPixCob;
        this.pixTransactionRepository = pixTransactionRepository;
    }
    @Override
    public PixTransaction pixPayment(TransactionDTO transactionDTO) {

        transactionDTO.setChave("9392925e-0257-417a-a180-506379529a2f");

        var transactionFinded = pixTransactionRepository.findByOrderId(transactionDTO.getOrderId());

        if (transactionFinded.isPresent()) {
            transactionFinded.get().setValor(transactionDTO.getValor().getOriginal());
            pixTransactionRepository.save(transactionFinded.get());
            return transactionFinded.get();
        }

        var pixTransactionDTO = efiPixCob.duePixCobv(transactionDTO);

        PixTransaction pixTransaction = new PixTransaction();
        pixTransaction.setExpiracao(pixTransactionDTO.calendario().expiracao());
        pixTransaction.setTxid(pixTransactionDTO.txid());
        pixTransaction.setRevisao(pixTransactionDTO.revisao());
        pixTransaction.setStatus(pixTransactionDTO.status());
        pixTransaction.setValor(pixTransactionDTO.valor().original());
        pixTransaction.setChave(pixTransaction.getChave());
        pixTransaction.setDevedor(pixTransactionDTO.devedor().nome());
        pixTransaction.setSolicitacaoPagador(pixTransactionDTO.solicitacaoPagador());
        pixTransaction.setLocation(pixTransactionDTO.loc().location());
        pixTransaction.setPixCopiaECola(pixTransactionDTO.pixCopiaECola());
        pixTransaction.setOrderId(transactionDTO.getOrderId());

        return pixTransactionRepository.save(pixTransaction);
    }

}

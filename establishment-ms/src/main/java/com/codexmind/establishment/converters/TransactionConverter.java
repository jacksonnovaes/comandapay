package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.PixTransaction;
import com.codexmind.establishment.dto.PixTransactionDTO;


public class TransactionConverter {

    public static PixTransactionDTO toDTO(PixTransaction pixTransaction) {
        var calendario = new PixTransactionDTO.Calendario(
                "",
                pixTransaction.getExpiracao()
        );
        var valor = new PixTransactionDTO.Valor(
                pixTransaction.getValor()
        );
        var devedor = new PixTransactionDTO.Devedor(
                pixTransaction.getCpf(),
                pixTransaction.getDevedor()
        );

        var loc = new PixTransactionDTO.Loc(
                0,
                pixTransaction.getLocation(),
                pixTransaction.getTipoCob(),
                pixTransaction.getCriacao()

        );
        return new PixTransactionDTO(
                calendario,
                pixTransaction.getTxid(),
                pixTransaction.getRevisao(),
                pixTransaction.getStatus(),
                valor,
                pixTransaction.getChave(),
                devedor,
                pixTransaction.getSolicitacaoPagador(),
                loc,
                pixTransaction.getPixCopiaECola()
        );
    }
}

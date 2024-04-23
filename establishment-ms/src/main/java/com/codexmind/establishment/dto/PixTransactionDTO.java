package com.codexmind.establishment.dto;

public record PixTransactionDTO(
        Calendario calendario,
        String txid,
        int revisao,
        String status,
        Valor valor,
        String chave,
        Devedor devedor,
        String solicitacaoPagador,
        Loc loc,
        String location,
        String pixCopiaECola
) {
    public record Calendario(
            String criacao,
            int expiracao
    ) {
    }

    public record Valor(
            String original
    ) {
    }

    public record Devedor(
            String cpf,
            String nome
    ) {
    }

    public record Loc(
            int id,
            String location,
            String tipoCob,
            String criacao
    ) {
    }
}


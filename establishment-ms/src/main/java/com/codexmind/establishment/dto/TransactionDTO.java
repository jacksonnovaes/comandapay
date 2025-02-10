package com.codexmind.establishment.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TransactionDTO {

    private String customerId;
    private String billingType;
    private CalendarioDTO calendario;
    private DevedorDTO devedor;
    private ValorDTO valor;
    private String chave;
    private String solicitacaoPagador;

    private Integer orderId;

    // Getters e Setters

    @Setter
    @Getter
    public static class CalendarioDTO {
        // Getters e Setters
        private int expiracao;

    }
    @Setter
    @Getter
    public static class DevedorDTO {
        private String cpf;
        private String nome;

    }
    @Setter
    @Getter
    public static class ValorDTO {
        private String original;


    }
}

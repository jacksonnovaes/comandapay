package com.codexmind.establishment.dto;

    public class TransactionDTO {

        private CalendarioDTO calendario;
        private DevedorDTO devedor;
        private ValorDTO valor;
        private String chave;
        private String solicitacaoPagador;
        private Integer orderId;

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        // Getters e Setters
        public CalendarioDTO getCalendario() {
            return calendario;
        }
    
        public void setCalendario(CalendarioDTO calendario) {
            this.calendario = calendario;
        }
    
        public DevedorDTO getDevedor() {
            return devedor;
        }
    
        public void setDevedor(DevedorDTO devedor) {
            this.devedor = devedor;
        }
    
        public ValorDTO getValor() {
            return valor;
        }
    
        public void setValor(ValorDTO valor) {
            this.valor = valor;
        }
    
        public String getChave() {
            return chave;
        }
    
        public void setChave(String chave) {
            this.chave = chave;
        }
    
        public String getSolicitacaoPagador() {
            return solicitacaoPagador;
        }
    
        public void setSolicitacaoPagador(String solicitacaoPagador) {
            this.solicitacaoPagador = solicitacaoPagador;
        }
    
        // Classes internas representando a estrutura do JSON
    
        public static class CalendarioDTO {
            private int expiracao;
    
            // Getters e Setters
            public int getExpiracao() {
                return expiracao;
            }
    
            public void setExpiracao(int expiracao) {
                this.expiracao = expiracao;
            }
        }
    
        public static class DevedorDTO {
            private String cpf;
            private String nome;
    
            // Getters e Setters
            public String getCpf() {
                return cpf;
            }
    
            public void setCpf(String cpf) {
                this.cpf = cpf;
            }
    
            public String getNome() {
                return nome;
            }
    
            public void setNome(String nome) {
                this.nome = nome;
            }
        }
    
        public static class ValorDTO {
            private String original;
    
            // Getters e Setters
            public String getOriginal() {
                return original;
            }
    
            public void setOriginal(String original) {
                this.original = original;
            }
        }
}

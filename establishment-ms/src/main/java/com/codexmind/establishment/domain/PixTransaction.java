package com.codexmind.establishment.domain;

import com.codexmind.establishment.dto.PixTransactionDTO;
import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;

@Entity
@Table(name = "TB_TRANSACTION")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PixTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String txid;
    private int revisao;
    private String status;
    private String valor;
    private String chave;
    private String devedor;
    private String cpf;
    private String solicitacaoPagador;
    private String location;
    private String pixCopiaECola;
    private String tipoCob;
    private String criacao;
    private int expiracao;

    private Integer orderId;

}

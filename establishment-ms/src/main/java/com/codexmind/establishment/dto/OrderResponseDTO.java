package com.codexmind.establishment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.codexmind.establishment.domain.enums.StatusComanda;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class OrderResponseDTO {

    private Integer id;

    private String customer;

    private String employee;

    private String establishment;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime instant;

    private StatusComanda statusComanda;

    private BigDecimal totalOrder;



}

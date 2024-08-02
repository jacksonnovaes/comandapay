package com.codexmind.establishment.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveProductListDTO {


    private String name;

    private BigDecimal price;

    private int estoque;

    private Integer establishmentId;
}

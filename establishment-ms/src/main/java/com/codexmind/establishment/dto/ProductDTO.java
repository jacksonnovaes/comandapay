package com.codexmind.establishment.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer menu;

    private String menuName;

    private Integer estoque;

    private Integer idMenu;
}

package com.codexmind.establishment.dto;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private Integer menu;
}

package com.codexmind.establishment.dto;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Integer id;

    private String name;

    public Integer establishment;

}

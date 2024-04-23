package com.codexmind.establishment.dto;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class CityDTO {

    private Long id;
    private String name;
    private Long state;

}

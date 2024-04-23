package com.codexmind.establishment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class UpdateEmployeeDTO {

    private String name;
    private String lastName;
    private String phone;
    private String celPhone;
    private Long establishment;
}

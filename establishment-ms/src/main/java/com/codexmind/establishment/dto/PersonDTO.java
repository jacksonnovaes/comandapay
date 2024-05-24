package com.codexmind.establishment.dto;

import com.codexmind.establishment.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public abstract class PersonDTO {

    private String name;
    private String lastName;
    private String cpf;
    private String phone;
    private String celPhone;
    private Status status;
    private String urlImage;
    private List<AddressDTO> addressList;


}

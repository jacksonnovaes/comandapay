package com.codexmind.establishment.dto;

import com.codexmind.establishment.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CustomerDTO extends PersonDTO {


    private LocalDate birthDate;

    @Builder
    public CustomerDTO(String name, String lastName, String cpf, String phone, Status status, String urlImage, String celPhone, List<AddressDTO> addressList, LocalDate birthDate) {
        super(name, lastName, cpf, phone, celPhone, status,urlImage, addressList);
        this.birthDate = birthDate;
    }
}


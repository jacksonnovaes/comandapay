package com.codexmind.establishment.dto;

import com.codexmind.establishment.domain.enums.Status;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class EmployeeDTO extends PersonDTO {


    private CargoDTO cargoDTO;

    private LocalDate admissionDate;

    @Builder(builderMethodName = "employeeDTOBuilder")
    public EmployeeDTO(String name, String lastName, String cpf, String phone, Status status,String urlImage, String celPhone, List<AddressDTO> addressList, CargoDTO cargoDTO, LocalDate admissionDate) {
        super(name, lastName, cpf, phone, celPhone, status, urlImage, addressList);
        this.cargoDTO = cargoDTO;
        this.admissionDate = admissionDate;
    }
}

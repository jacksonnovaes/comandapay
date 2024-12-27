package com.codexmind.establishment.converters;


import com.codexmind.establishment.domain.Cargo;
import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.dto.AddressDTO;
import com.codexmind.establishment.dto.EmployeeDTO;
import com.codexmind.establishment.dto.saveUpdate.SaveEmployeeDTO;
import com.codexmind.establishment.dto.saveUpdate.UpdateEmployeeDTO;

import java.time.LocalDate;
import java.util.List;

public class EmployeeConverter {

    public static Employee toEntity(SaveEmployeeDTO employeeDTO) {

        return Employee.builder()
                .id(null)
                .name(employeeDTO.name())
                .lastName(employeeDTO.lastName())
                .cpf(employeeDTO.cpf())
                .phone(employeeDTO.phone())
                .celPhone(employeeDTO.celPhone())
                .admissionDate(LocalDate.now())
                .cargo(Cargo.builder()
                        .name(employeeDTO.cargo())
                        .build())
                .addressList(List.of(
                        AddressConverter.toEntity(new AddressDTO(
                                employeeDTO.postalCode(),
                                employeeDTO.placeName(),
                                employeeDTO.number(),
                                employeeDTO.complemento(),
                                employeeDTO.bairro(),
                                employeeDTO.city(),
                                employeeDTO.uf()
                        ))))
                .build();

    }

    public static EmployeeDTO toDTO(Employee person) {
        return EmployeeDTO.employeeDTOBuilder()
                .name(person.getName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .phone(person.getPhone())
                .celPhone(person.getCelPhone())
                .admissionDate(person.getAdmissionDate())
                .addressList(AddressConverter.getAddressesToDto(person.getAddressList()))
                .cargoDTO(CargoDtoConverter.toDTO(person.getCargo()))
                .build();
    }

    public static EmployeeDTO toDTOResponse(Employee person) {
        return EmployeeDTO.employeeDTOBuilder()
                .name(person.getName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .phone(person.getPhone())
                .celPhone(person.getCelPhone())
                .admissionDate(person.getAdmissionDate())
                .urlImage(person.getUrlImage())
                .build();
    }

    public static UpdateEmployeeDTO toUpdateDTO(Employee employee) {
        return UpdateEmployeeDTO.builder()
                .name(employee.getName())
                .lastName(employee.getLastName())
                .phone(employee.getPhone())
                .celPhone(employee.getCelPhone())
                .admissionDate(employee.getAdmissionDate())
                .build();
    }

    public static Employee toEmployeeEntity(Employee person) {
        return Employee.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .phone(person.getPhone())
                .celPhone(person.getCelPhone())
                .admissionDate(person.getAdmissionDate())
                .addressList(
                        AddressConverter.getAddressesToEntity(person.getAddressList()))
                .cargo(person.getCargo())
                .build();
    }
}

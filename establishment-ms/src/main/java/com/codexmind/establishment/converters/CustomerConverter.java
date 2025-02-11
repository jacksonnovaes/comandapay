package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.enums.Status;
import com.codexmind.establishment.dto.AddressDTO;
import com.codexmind.establishment.dto.CustomerDTO;
import com.codexmind.establishment.dto.saveUpdate.SaveCustomerDTO;
import com.codexmind.establishment.dto.saveUpdate.UpdateCustomerDTO;

import java.util.List;

public class CustomerConverter {

    public static Customer toEntity(SaveCustomerDTO customerDTO) {
        return Customer.builder()
                .id(null)
                .name(customerDTO.name())
                .lastName(customerDTO.lastName())
                .cpf(customerDTO.cpfCnpj())
                .phone(customerDTO.phone())
                .celPhone(customerDTO.celPhone())
                .birthDate(customerDTO.birthDate())
                .addressList(List.of(
                                AddressConverter.toEntity(
                                        new
                                                AddressDTO(
                                                customerDTO.postalCode(),
                                                customerDTO.placeName(),
                                                customerDTO.number(),
                                                customerDTO.complemento(),
                                                customerDTO.bairro(),
                                                customerDTO.localidade(),
                                                customerDTO.uf()
                                        )
                                )
                        )
                )
                .build();

    }


    public static CustomerDTO toSaveCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .name(customer.getName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .celPhone(customer.getCelPhone())
                .build();
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .lastName(customerDTO.getLastName())
                .cpf(customerDTO.getCpf())
                .phone(customerDTO.getPhone())
                .celPhone(customerDTO.getCelPhone())
                .birthDate(customerDTO.getBirthDate())
                .build();

    }

    public static Customer toCustomerEntity(Customer person) {
        return Customer.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .phone(person.getPhone())
                .celPhone(person.getCelPhone())
                .birthDate(person.getBirthDate())
                .addressList(
                        AddressConverter.getAddressesToEntity(person.getAddressList()))
                .build();
    }


    public static CustomerDTO toDTO(Customer person) {
        return CustomerDTO.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .phone(person.getPhone())
                .celPhone(person.getCelPhone())
                .status(Status.ACTIVE)
                .birthDate(person.getBirthDate())
                .addressList(AddressConverter.getAddressesToDto(person.getAddressList()))
                .customerId(person.getCustomerId())
                .build();
    }

    public static CustomerDTO toResponseDTO(Customer person) {
        return CustomerDTO.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .build();
    }

    public static UpdateCustomerDTO toUpdateDTO(Customer customer) {
        return UpdateCustomerDTO.builder()
                .name(customer.getName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .celPhone(customer.getCelPhone())
                .birthDate(customer.getBirthDate())
                .build();
    }
}

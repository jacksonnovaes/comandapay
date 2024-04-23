package com.codexmind.establishment.converters;


import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.dto.AddressDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AddressConverter {

    public static List<AddressDTO> getAddressesToDto(List<Address> address) {
        return address.stream().map(AddressConverter::toDTO).collect(Collectors.toList());
    }

    public static List<Address> getAddressesToEntity(List<Address> addresses) {
        return addresses.stream().map(AddressConverter::toAddressesCustomer).collect(Collectors.toList());
    }

    public static AddressDTO toDTO(Address address) {
        return new AddressDTO(
                address.getName(),
                address.getComplemento(),
                address.getPostalCode(),
                address.getBairro(),
                address.getName(),
                address.getUf()
        );
    }

    public static Address toEntity(AddressDTO address) {
        return Address.builder()
                .id(null)
                .name(address.logradouro())
                .complemento(address.complemento())
                .postalCode(address.cep())
                .bairro(address.bairro())
                .city(address.localidade())
                .uf(address.uf())
                .build();
    }

    public static Address toAddressesCustomer(Address address) {
        return Address.builder()
                .name(address.getName())
                .complemento(address.getComplemento())
                .postalCode(address.getPostalCode())
                .bairro(address.getBairro())
                .city(address.getCity())
                .uf(address.getUf())
                .build();
    }
}

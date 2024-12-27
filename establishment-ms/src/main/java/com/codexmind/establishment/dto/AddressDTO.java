package com.codexmind.establishment.dto;

public record AddressDTO(

        String cep,
        String logradouro,
        String number,
        String complemento,
        String bairro,
        String localidade,
        String uf

) {
}

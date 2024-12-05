package com.codexmind.establishment.dto;

public record AddressDTO(

        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf

) {
}

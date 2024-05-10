package com.codexmind.establishment.dto;

public record ResponseEstablishmentDTO(

        Integer id,
        String name,

        String cnpj,

        Float rate,

        Boolean isFavorite,

        String path,

        String filename
)
{
}

package com.codexmind.establishment.dto;

public record AuthorizationDTO(
        String acessToken,
        String tokeType,
        int expires,
        String scope
) {
}

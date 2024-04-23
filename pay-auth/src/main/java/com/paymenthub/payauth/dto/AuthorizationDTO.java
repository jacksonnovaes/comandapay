package com.paymenthub.payauth.dto;

public record AuthorizationDTO(
    String acessToken,
    String tokeType,
    int expires,
    String scope

) {

}

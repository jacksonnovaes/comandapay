package com.codexmind.establishment.dto;

public record AppConfigDTO (
        Integer id,
        Integer establishmentId,
        String primaryColor,
        String secondaryColor,
        String contrastPrimaryColor,
        String contrastSecondaryColor,
        String mode,
        Boolean firstLogin
){
}

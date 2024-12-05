package com.codexmind.establishment.dto;

import java.util.UUID;

public record ResponseOpenCommanDTO(

        UUID id,

        Long establishmentId,

        Long customerId

) {
}

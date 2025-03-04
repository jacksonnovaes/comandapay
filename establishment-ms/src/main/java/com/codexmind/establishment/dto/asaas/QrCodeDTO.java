package com.codexmind.establishment.dto.asaas;

import java.time.LocalDateTime;

public record QrCodeDTO(
        String encodedImage,       // Imagem do QrCode em base64
        String payload,            // Copia e cola do QrCode
        String expirationDate // Data de expiração do QrCode
) {
}
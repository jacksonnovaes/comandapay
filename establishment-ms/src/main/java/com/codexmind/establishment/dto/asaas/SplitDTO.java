package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;

public record SplitDTO(
        String id,
        String walletId,
        BigDecimal fixedValue,
        BigDecimal percentualValue,
        BigDecimal totalValue,
        String cancellationReason,
        String status,
        String externalReference,
        String description
) {}

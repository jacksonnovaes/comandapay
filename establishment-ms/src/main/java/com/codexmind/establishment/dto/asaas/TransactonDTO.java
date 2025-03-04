package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactonDTO(
        BillingType billingType,
        BigDecimal value,
        String description
) {}

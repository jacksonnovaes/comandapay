package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RequestPixTransactonDTO(
        String customer,
        BillingType billingType,
        BigDecimal value,
        LocalDateTime dueDate,
        String description
) {}

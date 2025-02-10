package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestPixTransactonDTO(
        String customer,
        BillingType billingType,
        BigDecimal value,
        LocalDate dueDate,
        String description
) {}

package com.codexmind.establishment.dto;

import java.math.BigDecimal;

public record PayloadPixDTO(
        String customer,
        String billingType,
        BigDecimal value,
        String dueDate
) {
}


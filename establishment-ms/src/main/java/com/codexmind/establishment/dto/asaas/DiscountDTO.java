package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;

public record DiscountDTO(
        BigDecimal value,
        Integer dueDateLimitDays,
        String type
) {}

package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;

public record RefundedSplitDTO(
        String id,
        BigDecimal value,
        boolean done
) {}

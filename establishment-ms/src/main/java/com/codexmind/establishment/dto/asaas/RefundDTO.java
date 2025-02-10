package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;
import java.util.List;

public record RefundDTO(
        String dateCreated,
        String status,
        BigDecimal value,
        String endToEndIdentifier,
        String description,
        String effectiveDate,
        String transactionReceiptUrl,
        List<RefundedSplitDTO> refundedSplits
) {}

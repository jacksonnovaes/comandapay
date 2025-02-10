package com.codexmind.establishment.dto.asaas;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ChargebackDTO(
        String id,
        String payment,
        String installment,
        String customerAccount,
        String status,
        String reason,
        LocalDate disputeStartDate,
        BigDecimal value,
        LocalDate paymentDate,
        CreditCardDTO creditCard,
        String disputeStatus,
        LocalDate deadlineToSendDisputeDocuments
) {}

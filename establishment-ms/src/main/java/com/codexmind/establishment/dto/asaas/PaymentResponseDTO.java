package com.codexmind.establishment.dto.asaas;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponseDTO(
        String object,
        String id,
        LocalDate dateCreated,
        String customer,
        String subscription,
        String installment,
        String paymentLink,
        BigDecimal value,
        BigDecimal netValue,
        BigDecimal originalValue,
        BigDecimal interestValue,
        String description,
        String billingType,
        CreditCardDTO creditCard,
        boolean canBePaidAfterDueDate,
        String pixTransaction,
        String pixQrCodeId,
        String status,
        LocalDate dueDate,
        LocalDate originalDueDate,
        LocalDate paymentDate,
        LocalDate clientPaymentDate,
        Integer installmentNumber,
        String invoiceUrl,
        String invoiceNumber,
        String externalReference,
        boolean deleted,
        boolean anticipated,
        boolean anticipable,
        LocalDate creditDate,
        LocalDate estimatedCreditDate,
        String transactionReceiptUrl,
        String nossoNumero,
        String bankSlipUrl,
        DiscountDTO discount,
        FineDTO fine,
        InterestDTO interest,
        List<SplitDTO> split,
        boolean postalService,
        Integer daysAfterDueDateToRegistrationCancellation,
        ChargebackDTO chargeback,
        List<RefundDTO> refunds
) {}


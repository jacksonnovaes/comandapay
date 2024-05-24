package com.codexmind.establishment.dto;

import com.codexmind.establishment.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemOrderResponseDTO(
        Integer itemOrderid,
        String productName,
        BigDecimal price,
        Integer menuId,
        Integer idproduto,
        Integer orderId,
        Integer customerId,
        Integer employeeId,
        Integer establishmentId,
        PaymentStatus status,
        BigDecimal totalOrder,
        LocalDateTime openInstant,
        Integer quantity,
        BigDecimal totalAmount,
        BigDecimal discount,
        BigDecimal unitPrice
) {
}

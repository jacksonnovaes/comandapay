package com.codexmind.establishment.dto;

import java.math.BigDecimal;

public record ItemOrderDTO(
        Integer itemOrderId,
        String name,
        BigDecimal price,
        Integer menuId,
        Integer idProduto,
        Integer status,
        Integer orderId,
        Integer customerId,
        Integer employeeId,
        Integer establishmentId,
        BigDecimal totalOrder,
        String openInstant,
        Integer productId,
        Integer quantity,
        BigDecimal totalAmount,
        BigDecimal discount,
        BigDecimal unitPrice
) {
}

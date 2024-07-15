package com.codexmind.establishment.dto;

public record ItemOrderDTO(
        int itemOrderId,
        String name,
        double price,
        int menuId,
        int idProduto,
        int status,
        int orderId,
        int customerId,
        Integer employeeId,
        int establishmentId,
        int orderStatus,
        double totalOrder,
        String openInstant,
        int productId,
        int quantity,
        double totalAmount,
        Double discount,
        double unitPrice
) {
}

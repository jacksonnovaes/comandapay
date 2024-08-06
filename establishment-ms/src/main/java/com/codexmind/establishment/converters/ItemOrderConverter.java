package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderDTO;
import com.codexmind.establishment.dto.ItemOrderRequestDTO;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemOrderConverter {
    public static Set<ItemOrderResponseDTO> toDTO(Set<ItemOrder> itemOrderList) {
        return itemOrderList.stream()
                .map(itemOrder -> new ItemOrderResponseDTO(
                        itemOrder.getItemOrderId(),
                        itemOrder.getProduct().getName(),
                        itemOrder.getProduct().getPrice(),
                        itemOrder.getProduct().getId(),
                        itemOrder.getOrder().getId(),
                        itemOrder.getOrder().getCustomer() != null ?
                                itemOrder.getOrder().getCustomer().getId() : null,
                        itemOrder.getEmployeeId(),
                        itemOrder.getOrder().getEstablishment().getId(),
                        itemOrder.getPaymentStatus(),
                        itemOrder.getOrder().getTotalOrder(),
                        itemOrder.getOrder().getOpenInstant(),
                        itemOrder.getQuantity(),
                        itemOrder.getTotalAmount(),
                        itemOrder.getDiscount(),
                        itemOrder.getUnitPrice()
                ))
                .collect(Collectors.toSet());
    }


    public static List<ItemOrderResponseDTO> toDTOSets(List<ItemOrder> itemOrderList) {
        return itemOrderList.stream()
                .map(itemOrder -> new ItemOrderResponseDTO(
                        itemOrder.getItemOrderId(),
                        itemOrder.getProductName(),
                        itemOrder.getPrice(),
                        itemOrder.getProduct().getId(),
                        itemOrder.getOrder().getId(),
                        itemOrder.getCustomerId(),
                        itemOrder.getEmployeeId(),
                        itemOrder.getEstablishmentId(),
                        itemOrder.getPaymentStatus(),
                        itemOrder.getTotalOrder(),
                        itemOrder.getOpenInstant(),
                        itemOrder.getQuantity(),
                        itemOrder.getTotalAmount(),
                        itemOrder.getDiscount(),
                        itemOrder.getUnitPrice()
                ))
                .collect(Collectors.toList());
    }

    public static ItemOrderResponseDTO toDTO(ItemOrder itemOrder) {
        if (itemOrder == null) {
            return null;
        }
        return new ItemOrderResponseDTO(
                itemOrder.getItemOrderId(),
                itemOrder.getProductName(),
                itemOrder.getPrice(),
                itemOrder.getProduct().getId(),
                itemOrder.getOrder().getId(),
                itemOrder.getCustomerId(),
                itemOrder.getEmployeeId(),
                itemOrder.getEstablishmentId(),
                itemOrder.getPaymentStatus(),
                itemOrder.getTotalOrder(),
                itemOrder.getOpenInstant(),
                itemOrder.getQuantity(),
                itemOrder.getTotalAmount(),
                itemOrder.getDiscount(),
                itemOrder.getUnitPrice()
        );
    }


}


package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.dto.ItemOrderRequestDTO;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemOrderConverter {
    public static Set<ItemOrderResponseDTO> toDTO(Set<ItemOrder> itemOrderList) {
        return itemOrderList.stream()
                .map(itemOrder -> new ItemOrderResponseDTO(
                        itemOrder.getId(),
                        itemOrder.getProduct().getName(),
                        itemOrder.getPrice(),
                        itemOrder.getProduct().getMenu().getId(),
                        itemOrder.getProduct().getId(),
                        itemOrder.getOrder().getId(),
                        itemOrder.getCustomerId(),
                        itemOrder.getEmployeeId(),
                        itemOrder.getEstablishmentId(),
                        itemOrder.getStatus(),
                        itemOrder.getTotalOrder(),
                        itemOrder.getOpenInstant(),
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
                        itemOrder.getId(),
                        itemOrder.getProduct().getName(),
                        itemOrder.getPrice(),
                        itemOrder.getProduct().getMenu().getId(),
                        itemOrder.getProduct().getId(),
                        itemOrder.getOrder().getId(),
                        itemOrder.getCustomerId(),
                        itemOrder.getEmployeeId(),
                        itemOrder.getEstablishmentId(),
                        itemOrder.getStatus(),
                        itemOrder.getTotalOrder(),
                        itemOrder.getOpenInstant(),
                        itemOrder.getQuantity(),
                        itemOrder.getTotalAmount(),
                        itemOrder.getDiscount(),
                        itemOrder.getUnitPrice()
                ))
                .collect(Collectors.toList());
    }

    public  static ItemOrderResponseDTO toDTO(ItemOrder itemOrder){
        if(itemOrder==null){
            return null;
        }
        return new ItemOrderResponseDTO(
                itemOrder.getId(),
                itemOrder.getProduct().getName(),
                itemOrder.getPrice(),
                itemOrder.getProduct().getMenu().getId(),
                itemOrder.getProduct().getId(),
                itemOrder.getOrder().getId(),
                itemOrder.getCustomerId(),
                itemOrder.getEmployeeId(),
                itemOrder.getEstablishmentId(),
                itemOrder.getStatus(),
                itemOrder.getTotalOrder(),
                itemOrder.getOpenInstant(),
                itemOrder.getQuantity(),
                itemOrder.getTotalAmount(),
                itemOrder.getDiscount(),
                itemOrder.getUnitPrice()
        );
    }

}

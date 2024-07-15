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
                        itemOrder.getId(),
                        itemOrder.getProductName(),
                        itemOrder.getPrice(),
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
                        itemOrder.getProductName(),
                        itemOrder.getPrice(),
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

    public static ItemOrderResponseDTO toDTO(ItemOrder itemOrder) {
        if (itemOrder == null) {
            return null;
        }
        return new ItemOrderResponseDTO(
                itemOrder.getId(),
                itemOrder.getProductName(),
                itemOrder.getPrice(),
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

    public static List<ItemOrderDTO> toDTO(List<Map<String, Object>> items) {

        List<ItemOrderDTO> dtos = new ArrayList<>();

        for (Map<String, Object> item : items) {
                    ItemOrderDTO dto = new ItemOrderDTO(
                    (Integer) item.get("itemorderid"),
                    (String) item.get("name"),
                    (Double) item.get("price"),
                    (Integer) item.get("menu_id"),
                    (Integer) item.get("idproduto"),
                    (Integer) item.get("status"),
                    (Integer) item.get("id"),
                    (Integer) item.get("customer_id"),
                    (Integer) item.get("employee_id"),
                    (Integer) item.get("establishment_id"),
                    (Integer) item.get("status"),
                    (Double) item.get("total_order"),
                    (String) item.get("open_instant"),
                    (Integer) item.get("product_id"),
                    (Integer) item.get("quantity"),
                    (Double) item.get("total_amount"),
                    (Double) item.get("discount"),
                    (Double) item.get("unit_price")
            );

            dtos.add(dto);
        }
            return dtos;
    }
}


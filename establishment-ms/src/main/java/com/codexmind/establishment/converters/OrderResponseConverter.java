package com.codexmind.establishment.converters;


import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.dto.OrderResponseDTO;

public class OrderResponseConverter {

    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .establishment(order.getEstablishment().getName())
                .customer(order.getCustomer() != null ?
                        (order.getCustomer().getName() != null ? order.getCustomer().getName() : "") +
                                " " +
                                (order.getCustomer().getLastName() != null ? order.getCustomer().getLastName() : "") :
                        null)
                .employee(order.getEmployee() != null ?
                        (order.getEmployee().getName() != null ? order.getEmployee().getName() : "") +
                                " " +
                                (order.getEmployee().getLastName() != null ? order.getEmployee().getLastName() : "") :
                        null)
                .instant(order.getOpenInstant())
                .statusComanda(order.getStatus())
                .totalOrder(order.getTotalOrder())
                .build();
    }
}

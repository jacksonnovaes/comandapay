package com.codexmind.establishment.converters;


import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.dto.OrderResponseDTO;

import java.util.Collections;

public class OrderResponseConverter {

    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .establishment(order.getEstablishment().getName())
                .customer(order.getCustomer().getName()
                        + " "+ order.getCustomer().getLastName())
                .instant(order.getOpenInstant())
                .statusComanda(order.getStatus())
                .build();
    }
}

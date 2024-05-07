package com.codexmind.establishment.converters;


import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.OrderResponseDTO;
import com.codexmind.establishment.dto.ProductDTO;

public class OrderResponseConverter {

    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .employee(order.getEmployee()
                        != null ? order.getEmployee().getName() +" "+ order.getEmployee().getLastName(): null)
                .customer(order.getCustomer()
                        != null ? order.getCustomer().getName() +" "+ order.getCustomer().getLastName(): null)
                .productIds(ProductConverter.toDTOSet(order.getProducts()))
                .establishment(order.getEstablishment().getName())
                .instant(order.getInstant())
                .build();
    }
}

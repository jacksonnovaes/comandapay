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
                        != null ? order.getEmployee().getId(): null)
                .customer(order.getCustomer()
                        != null ? order.getCustomer().getId(): null)
                .productIds(ProductConverter.toDTOSet(order.getProducts()))
                .build();
    }
}

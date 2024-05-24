package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.ProductDTO;

import java.util.HashSet;
import java.util.Set;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .menu(product.getMenu().getId())
                .price(product.getPrice())
                .menuName(product.getMenu().getName())
                .build();
    }

    public static Set<ProductDTO> toDTOSet(Set<Product> products) {
        Set<ProductDTO> dtoSet = new HashSet<>();
        for (Product product : products) {
            dtoSet.add(toDTO(product));
        }
        return dtoSet;
    }
}

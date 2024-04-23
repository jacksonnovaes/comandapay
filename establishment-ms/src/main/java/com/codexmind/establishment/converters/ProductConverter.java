package com.codexmind.establishment.converters;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.ProductDTO;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .menu(product.getMenu().getId())
                .build();
    }
}

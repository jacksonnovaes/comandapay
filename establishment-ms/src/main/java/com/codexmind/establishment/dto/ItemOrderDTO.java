package com.codexmind.establishment.dto;

import com.codexmind.establishment.domain.ItemOrderPk;

public record ItemOrderDTO(
        ItemOrderPk id,
        Double discount,
        Integer quantity,
        Double price,

        ProductDTO productDTO

) {
}

package com.codexmind.establishment.dto;

import java.math.BigDecimal;
import java.util.List;

public record ResponseCommandOrderDTO(
        List<ProductDTO> products,

        BigDecimal subTotal,

        EstablishmentDTO establishment

)
{
}

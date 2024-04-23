package com.codexmind.establishment.dto.saveUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SaveIngredientDTO {

    private String UUID;

    private String description;

    private BigDecimal price;
}


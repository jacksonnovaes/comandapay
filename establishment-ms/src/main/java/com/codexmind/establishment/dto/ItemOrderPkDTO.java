package com.codexmind.establishment.dto;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ItemOrderPkDTO {

   
    private OrderDTO order;


    private ProductDTO product;

}

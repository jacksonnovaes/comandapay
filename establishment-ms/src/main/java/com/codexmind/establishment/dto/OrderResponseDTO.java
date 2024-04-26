package com.codexmind.establishment.dto;

import java.util.Set;

import com.codexmind.establishment.domain.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class OrderResponseDTO {

             private Long id;

             private Long employee;

             private Long customer;

             private Set<ProductDTO> productIds;

             private String establishment;

}

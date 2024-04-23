package com.codexmind.establishment.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {

             private Long id;

             private Long addressId;

             private Long customerId;

             private Set<Long> productIds;

}

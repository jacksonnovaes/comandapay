package com.codexmind.establishment.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {

             private Integer orderId;
             private Integer addressId;

             private Integer customerId;

             private Set<Integer> productIds;

}

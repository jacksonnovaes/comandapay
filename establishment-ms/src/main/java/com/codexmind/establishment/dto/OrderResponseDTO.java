package com.codexmind.establishment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.codexmind.establishment.domain.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class OrderResponseDTO {

             private Integer id;

             private String employee;

             private String customer;

             private Set<ProductDTO> productIds;

             private String establishment;
                @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
             private LocalDateTime instant;

}

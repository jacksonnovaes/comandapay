package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "tb_orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyy HH:mm")
    private LocalDateTime openInstant;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Enumerated(EnumType.ORDINAL)
    private  StatusComanda status;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    private BigDecimal totalOrder;

}

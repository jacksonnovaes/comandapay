package com.codexmind.establishment.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private LocalDateTime instant;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="tb_item_order",
    joinColumns = @JoinColumn(name="order_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
@JsonBackReference
    private Set<Product> products = new LinkedHashSet<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

}

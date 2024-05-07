package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_establishment")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String cnpj;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "establishment")
    private List<Menu> menus = new ArrayList<>();

    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "establishment",cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "establishment")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

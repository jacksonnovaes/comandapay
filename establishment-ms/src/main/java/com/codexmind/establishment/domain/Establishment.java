package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "establishment")
    private List<Menu> menus = new ArrayList<>();

    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "establishment",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "establishment")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Boolean isFavorite = Boolean.FALSE;

    private Float rate;

    private String urlImage;
    
    @OneToMany(mappedBy = "establishment")
    private Set<Estoque> estoques = new LinkedHashSet<>();

    public int getAllOrderCount(){
        return orders.size();
    }
}

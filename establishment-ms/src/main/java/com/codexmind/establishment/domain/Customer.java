package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@DiscriminatorValue(Customer.CUSTOMER)
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person {

    private LocalDate birthDate;

    public static final String CUSTOMER = "CUSTOMER";


    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orderList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Establishment> favorites = new HashSet<>();

    @Builder
    public Customer(Integer id,
                    String name,
                    String lastName,
                    String cpf,
                    String phone,
                    String celPhone,
                    Status status,
                    User user,
                    String urlImage,
                    Set<Integer> profiles,
                    LocalDate birthDate,
                    List<Address> addressList,
                    List<Order> orderList,
                    Set<Establishment> favorites
    ) {
        super(id, name, lastName, cpf, phone, celPhone, status, user, urlImage, profiles);
        this.birthDate = birthDate;
        this.addressList = addressList;
        this.orderList = orderList;
        this.favorites = favorites;

    }
}


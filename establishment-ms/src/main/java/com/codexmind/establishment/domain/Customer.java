package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();



    @Builder
    public Customer(
            Long id,
            String name,
            String lastName,
            String cpf,
            String phone,
            String celPhone,
            Status status,
            Establishment establishment,
            User user,
            List<Address> addressList,
            Set<Integer> profiles,
            LocalDate birthDate) {
        super(id, name, lastName, cpf, phone, celPhone, status, establishment, user, profiles);
        this.addressList = addressList;
        this.birthDate = birthDate;

    }
}


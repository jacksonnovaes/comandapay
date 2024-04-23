package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@DiscriminatorValue("EMPLOYEE")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee extends Person {

    private LocalDate admissionDate;

    @OneToMany(mappedBy = "employee")
    private List<Address> addressList =new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;


    @Builder
    public Employee(
            Long id,
            String name,
            String lastName,
            String cpf,
            String phone,
            String celPhone,
            Status status,
            Establishment establishment,
            User user,
            Set<Integer> profiles,
            LocalDate admissionDate,
            List<Address> addressList,
            Cargo cargo) {
        super(id, name, lastName, cpf, phone, celPhone, status, establishment, user, profiles);
        this.admissionDate = admissionDate;
        this.addressList = addressList;
        this.cargo = cargo;
    }
}

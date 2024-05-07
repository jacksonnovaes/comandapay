package com.codexmind.establishment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "tb_address")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 2662999979467455549L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String number;
    private String name;
    private String postalCode;

    private String complemento;

    private String bairro;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    private String city;

    private String uf;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }
}

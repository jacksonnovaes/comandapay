package com.codexmind.establishment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private int quantity;

    @ManyToOne
    private Establishment establishment;

    @OneToMany(mappedBy = "estoque")
    private Set<Product> products = new LinkedHashSet<>();
}

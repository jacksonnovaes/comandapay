package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_menu")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Status status;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}

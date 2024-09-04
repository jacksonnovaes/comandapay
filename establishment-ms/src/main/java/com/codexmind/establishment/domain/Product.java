package com.codexmind.establishment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tb_product")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @Column(name = "qtd_estoque")
    private Integer estoque;

    @ManyToOne(fetch = FetchType.LAZY)
    private Services service;

}
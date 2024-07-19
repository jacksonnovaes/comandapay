package com.codexmind.establishment.domain;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="tb_item_order")
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal discount;

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalAmount;

    private PaymentStatus paymentStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    // Assuming PRD.NAME maps to productName
    @Transient
    private String productName;

    // Assuming PRD.PRICE maps to price
    @Transient
    private BigDecimal price;

    // Additional transient fields for ORD fields
    @Transient
    private Integer customerId;

    @Transient
    private Integer employeeId;

    @Transient
    private Integer establishmentId;

    @Transient
    private BigDecimal totalOrder;

    @Transient
    private LocalDateTime openInstant;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return Objects.equals(id, itemOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

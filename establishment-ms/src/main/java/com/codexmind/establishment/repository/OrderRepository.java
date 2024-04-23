package com.codexmind.establishment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codexmind.establishment.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}

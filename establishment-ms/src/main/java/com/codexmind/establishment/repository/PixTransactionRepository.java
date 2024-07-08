package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.PixTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixTransactionRepository extends JpaRepository<PixTransaction, Integer> {
    Optional<PixTransaction> findByOrderId(Integer orderId);
}

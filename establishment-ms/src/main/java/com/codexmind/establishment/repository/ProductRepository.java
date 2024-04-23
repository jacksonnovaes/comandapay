package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


 @Query(
         "SELECT distinct p FROM Product p INNER JOIN Menu m INNER JOIN Establishment e where e.id = ?1 ")
 Page<Product> getAllProductsByMenuAndEstablishment(Long id,
                                                    Pageable pageable);
 @Query("SELECT SUM(p.price) FROM Product p WHERE p.id IN :productIds")
 BigDecimal getTotalAmountByIds(Set<Long> productIds);
}

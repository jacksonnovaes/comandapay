package com.codexmind.establishment.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codexmind.establishment.domain.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    /**
     * SELECT * FROM TB_ORDERS ORD INNER JOIN TB_ITEM_ORDER ITEM ON ORD.ID =  ITEM.ORDER_ID
     * INNER JOIN TB_PRODUCT PROD ON PROD.ID = ITEM.PRODUCT_ID
     * INNER JOIN TB_MENU ME ON ME.ID= PROD.MENU_ID
     * INNER JOIN TB_ESTABLISHMENT EST ON EST.ID = ME.ESTABLISHMENT_ID
     * INNER JOIN TB_PERSON PERS ON PERS.ESTABLISHMENT_ID = EST.ID
     * @return
     */

        @Query(value = """
                 SELECT ORD.ID as `ordenid`, E.ADDRESS_ID, ORD.CUSTOMER_ID, ORD.EMPLOYEE_ID,
                 ORD.INSTANT, ORD.ESTABLISHMENT_ID, PER.NAME, PER.LAST_NAME
                 FROM TB_ORDERS ORD
                  INNER JOIN TB_ESTABLISHMENT E ON E.ID = ORD.ESTABLISHMENT_ID
                  INNER JOIN TB_PERSON PER ON PER.ID = ORD.CUSTOMER_ID
                 WHERE E.ID = ?1 AND ORD.INSTANT BETWEEN ?2 AND ?3""", nativeQuery = true)
        Page<Order> getAllOrdersByEstablishmentId(Integer id , LocalDateTime initialInst, LocalDateTime endInstant, Pageable pageable);


}

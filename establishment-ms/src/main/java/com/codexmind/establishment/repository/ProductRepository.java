package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


 @Query(
         "SELECT distinct p FROM Product p INNER JOIN p.menu m INNER JOIN m.establishment e where e.id = ?1 ")
 Page<Product> getAllProductsByMenuAndEstablishment(Integer id,
                                                    Pageable pageable);
 @Query("SELECT SUM(p.price) FROM Product p WHERE p.id IN :productIds")
 BigDecimal getTotalAmountByIds(Set<Long> productIds);


 @Query(value = """
      select p.id, p.name, p.price, p.menu_id
      from tb_product
      p inner join tb_item_order tio on tio.product_id = p.id
      inner join tb_orders io on io.id = tio.order_id
      where io.id=?1
""", nativeQuery = true)
 Set<Product> getAllProductsByIdOrder(Integer id);

 @Query(value = """
         select prod.id, prod.name, prod.price, prod.menu_id,
         menu.name as menu
         from tb_product prod
         left join tb_menu menu
         on prod.menu_id = menu.id
         inner join tb_establishment
         estab on estab.id = menu.establishment_id
         where estab.id = ?1
         """, nativeQuery = true)
 Set<Product> getProductdsByMenuandEstablishmente(Integer id);



}

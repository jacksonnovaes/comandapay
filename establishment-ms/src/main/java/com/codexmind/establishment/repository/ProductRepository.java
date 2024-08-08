package com.codexmind.establishment.repository;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codexmind.establishment.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


 @Query(
         "SELECT distinct p FROM Product p INNER JOIN p.menu m INNER JOIN m.establishment e where e.id = ?1 AND p.estoque > 0")
 Page<Product> getAllProductsByMenuAndEstablishment(Integer id,
                                                    Pageable pageable);


 @Query("SELECT SUM(p.price) FROM Product p WHERE p.id IN :productIds")
 BigDecimal getTotalAmountByIds(Set<Long> productIds);


 @Query(value = """
      select p.id, p.name, p.price, p.menu_id,
      p.qtd_estoque
      from tb_product
      p inner join tb_item_order tio on tio.product_id = p.id
      inner join tb_orders io on io.id = tio.order_id
      where io.id=?1
""", nativeQuery = true)
 Set<Product> getAllProductsByIdOrder(Integer id);

 @Query(value = """
         select prod.id, prod.name, prod.price, prod.menu_id, prod.qtd_estoque,
         menu.name as menu
         from tb_product prod
         left join tb_menu menu
         on prod.menu_id = menu.id
         inner join tb_establishment
         estab on estab.id = menu.establishment_id
         where estab.id = ?1
         """, nativeQuery = true)
 Set<Product> getProductdsByMenuandEstablishmente(Integer id);


 @Query(value = """
         select prod.id, prod.name, prod.price, prod.menu_id,
         prod.qtd_estoque,
         menu.name as menu
         from tb_product prod
         left join tb_menu menu
         on prod.menu_id = menu.id
         inner join tb_establishment
         estab on estab.id = menu.establishment_id
         where menu.id = ?1 and (prod.qtd_estoque > 0 OR prod.qtd_estoque IS NULL)
         """, nativeQuery = true)
 Page<Product> getProductsByMenu(Integer id, Pageable pageable);

 @Query(value = """
         select prod.id, prod.name, prod.price, prod.menu_id,
         prod.qtd_estoque,
         menu.name as menu
         from tb_product prod
         left join tb_menu menu
         on prod.menu_id = menu.id
         inner join tb_establishment
         estab on estab.id = menu.establishment_id
         where prod.name ilike %?1% and prod.menu_id=?2 and (prod.qtd_estoque > 0 OR prod.qtd_estoque IS NULL)
         """, nativeQuery = true)
 Page<Product> searchProducts(String name, Integer idMenu, Pageable pageable);
 @Query(value = """
         select prod.id, prod.name, prod.price, prod.menu_id,
         prod.qtd_estoque,
         menu.name as menu
         from tb_product prod
         left join tb_menu menu
         on prod.menu_id = menu.id
         inner join tb_establishment
         estab on estab.id = menu.establishment_id
         where prod.name ilike %?1% and prod.menu_id=?2
         """, nativeQuery = true)
 Page<Product> searchProductsEstoque(String name, Integer idMenu, Pageable pageable);


 @Query(value = """
         select prod.id, prod.name, prod.price, prod.menu_id,
         prod.qtd_estoque,
         menu.name as menu
         from tb_product prod
         left join tb_menu menu
         on prod.menu_id = menu.id
         inner join tb_establishment
         estab on estab.id = menu.establishment_id
         where menu.id = ?1
         """, nativeQuery = true)
 Page<Product> getEstoqueByMenu(Integer id, Pageable pageable);
}

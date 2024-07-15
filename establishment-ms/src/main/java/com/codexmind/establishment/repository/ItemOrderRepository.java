package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.enums.StatusComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {

    List<ItemOrder> findByOrder(Order order);

    @Query(nativeQuery = true, value = """
            select distinct tio.id as itemorderid, prd.name, prd.price, prd.menu_id, prd.id as idproduto,tio.status, ord.id, ord.customer_id, ord.employee_id, ord.establishment_id,
                                                          ord.status , ord.total_order, ord.open_instant, tio.id as itemorderid,
                                                          tio.order_id, tio.product_id, tio.quantity, tio.total_amount,
                                                          tio.discount, tio.unit_price
                                                          from tb_item_order tio left join tb_orders ord on ord.id = tio.order_id
                                                          left join tb_product prd on prd.id = tio.product_id
                                                                      where ord.customer_id = ?1  and ord.status = ?2""")
    List<Map<String, Object>> getAllItemOrders(Integer id, StatusComanda status);


}

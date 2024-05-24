package com.codexmind.establishment.usecases.ItemOrder;

import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetItemOrder {

    private final ItemOrderRepository itemOrderRepository;
    public List<Map<String, ItemOrder>> execute(Integer idOrder){
       var items = itemOrderRepository.getAllItemOrders(idOrder, StatusComanda.OPENED);
        return items;
    }


}

package com.codexmind.establishment.usecases.ItemOrder;

import com.codexmind.establishment.converters.ItemOrderConverter;
import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderDTO;
import com.codexmind.establishment.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetItemOrder {

    private final ItemOrderRepository itemOrderRepository;
    public List<ItemOrderDTO>  execute(Integer idOrder){
        List<Map<String, Object>> items = itemOrderRepository.getAllItemOrders(idOrder, StatusComanda.OPENED);
        List<ItemOrderDTO> itemDtos = ItemOrderConverter.toDTO(items);
        return itemDtos;
    }


}

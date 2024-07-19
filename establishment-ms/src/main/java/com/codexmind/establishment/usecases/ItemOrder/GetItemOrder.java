package com.codexmind.establishment.usecases.ItemOrder;

import com.codexmind.establishment.converters.ItemOrderConverter;
import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderDTO;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetItemOrder {

    private final ItemOrderRepository itemOrderRepository;
    public Set<ItemOrderResponseDTO> execute(Integer idOrder){
        var items = itemOrderRepository.getAllItemOrders(idOrder, StatusComanda.OPENED);

        return ItemOrderConverter.toDTO(items);
    }


}

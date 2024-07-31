package com.codexmind.establishment.usecases.order.pdv;

import com.codexmind.establishment.converters.ItemOrderConverter;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetItemOrderPdv {

    private final ItemOrderRepository itemOrderRepository;
    public Set<ItemOrderResponseDTO> execute(Integer idOrder){
        var items = itemOrderRepository.getAllItemOrdersByOrderId(idOrder, StatusComanda.OPENED);

        return ItemOrderConverter.toDTO(items);
    }


}

package com.codexmind.establishment.usecases.ItemOrder;

import com.codexmind.establishment.converters.ItemOrderConverter;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetItemOrder {

    private final ItemOrderRepository itemOrderRepository;

    public Set<ItemOrderResponseDTO> execute(Integer customerId) {
        var items = itemOrderRepository.getAllItemOrdersByCustomer(customerId, StatusComanda.OPENED, PaymentStatus.PENDING);

        return ItemOrderConverter.toDTO(items);
    }


}

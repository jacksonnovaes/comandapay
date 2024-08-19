package com.codexmind.establishment.usecases.ItemOrder;

import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.ItemOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EditItemOrder {

    private final ItemOrderRepository itemOrderRepository;

    @Transactional
    public ItemOrder execute(Integer itemOrderId, int intQuantity){
        var itemOrderFinded = itemOrderRepository.findById(itemOrderId)
                .orElseThrow(()-> new EntityNotFoundException("Item nao encontrado"));
        if(intQuantity==0) {
            itemOrderFinded.setOrder(null);
            itemOrderRepository.delete(itemOrderFinded);
            return null;
        }
        itemOrderFinded.getOrder().setTotalOrder(itemOrderFinded.getUnitPrice().multiply(BigDecimal.valueOf(intQuantity)));
        itemOrderFinded.setItemOrderId(itemOrderId);
        itemOrderFinded.setQuantity(intQuantity);
        itemOrderFinded.setTotalAmount(itemOrderFinded.getUnitPrice().multiply(BigDecimal.valueOf(intQuantity)));

        return itemOrderRepository.save(itemOrderFinded);
    }
}

package com.codexmind.establishment.usecases.order.pdv;

import com.codexmind.establishment.converters.ItemOrderConverter;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.codexmind.establishment.domain.enums.PaymentStatus.PAID;
import static com.codexmind.establishment.domain.enums.PaymentStatus.PENDING;
import static com.codexmind.establishment.domain.enums.StatusComanda.CLOSED;

@Service
@RequiredArgsConstructor
public class GetItemOrderPdv {

    private final ItemOrderRepository itemOrderRepository;
    public Set<ItemOrderResponseDTO> execute(Integer idOrder,String status){

       var  statusComanda = StatusComanda.fromValue(status);
        var statusPayment = statusComanda == CLOSED ? PAID : PENDING;
        var items = itemOrderRepository.getAllItemOrdersByOrderId(idOrder, statusComanda, statusPayment);

        return ItemOrderConverter.toDTO(items);
    }


}

package com.codexmind.establishment.usecases.order;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllOrdersByUser {

    private final OrderRepository orderRepository;

    public Page<Order> execute(Long id,Integer page, Integer linesPerPge, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), orderBy);
        return  orderRepository.getAllOrdersByEstablishmentAndPersonId(id, pageRequest);
    }
}

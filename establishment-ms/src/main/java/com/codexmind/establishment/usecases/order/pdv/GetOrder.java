package com.codexmind.establishment.usecases.order.pdv;


import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrder {

    private final OrderRepository orderRepository;


    public Order execute(Integer orderId) {

        return orderRepository.findById(orderId).get();

    }
}

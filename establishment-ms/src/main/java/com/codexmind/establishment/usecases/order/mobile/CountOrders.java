package com.codexmind.establishment.usecases.order.mobile;

import com.codexmind.establishment.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountOrders {

    private final OrderRepository orderRepository;

    public int execute(Integer userId) {
        return orderRepository.countOrderByUser(userId);
    }
}

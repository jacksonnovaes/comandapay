package com.codexmind.establishment.usecases.order;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.usecases.establishment.GetEstablishmentByEmployeLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GetAllOrdersByUser {

    private final OrderRepository orderRepository;

    private final GetEstablishmentByEmployeLogin getEstablishmentByEmployeLogin;

    public Page<Order> execute(Integer page, Integer linesPerPge, String orderBy, String direction){

        var id = getEstablishmentByEmployeLogin.execute();
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), orderBy);
        LocalDateTime startInstant = LocalDateTime.now().minusHours(3);
        LocalDateTime endInstant = LocalDateTime.now();

        System.out.println(startInstant);

        System.out.println(endInstant);

        return  orderRepository.getAllOrdersByEstablishmentId(id, startInstant ,endInstant,  pageRequest);
    }
}

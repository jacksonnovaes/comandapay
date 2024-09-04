package com.codexmind.establishment.usecases.order.pdv;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.usecases.establishment.GetEstablishmentByEmployeLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllOrdersByEmployee {

    private final OrderRepository orderRepository;

    private final GetEstablishmentByEmployeLogin getEstablishmentByEmployeLogin;

    public Page<Order> execute(Integer page, Integer linesPerPge, String orderBy, String direction, String status){

        var establishment = getEstablishmentByEmployeLogin.execute();
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), orderBy);
        LocalDateTime startInstant = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endInstant = LocalDateTime.now().with(LocalTime.MAX);

        var statusComanda = StatusComanda.fromValue(status);
        return orderRepository.getAllOrdersByEmployeeId(establishment.get().getId(), startInstant, endInstant, pageRequest, statusComanda);

    }
}

package com.codexmind.establishment.usecases.order.mobile;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.usecases.establishment.GetEstablishmentByEmployeLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllOrdersByUser {

    private final OrderRepository orderRepository;

    private final GetEstablishmentByEmployeLogin getEstablishmentByEmployeLogin;

    public Page<Order> execute(Integer page, Integer linesPerPge, String orderBy, String direction){

        var establishment = getEstablishmentByEmployeLogin.execute();
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), orderBy);
        LocalDateTime startInstant = LocalDateTime.now().minusHours(3);
        LocalDateTime endInstant = LocalDateTime.now().plusMinutes(5);
        LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Convert LocalDateTime to Timestamp
        Timestamp startTimestamp = Timestamp.valueOf(startInstant);
        Timestamp endTimestamp = Timestamp.valueOf(endInstant);

        log.info("Start Timestamp: {}", startTimestamp);
        log.info("End Timestamp: {}", endTimestamp);

        return orderRepository.getAllOrdersByEstablishmentId(establishment.get().getId(), pageRequest);

    }
}

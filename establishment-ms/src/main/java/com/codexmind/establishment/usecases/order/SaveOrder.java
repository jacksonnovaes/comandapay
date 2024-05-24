package com.codexmind.establishment.usecases.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.codexmind.establishment.domain.*;
import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.*;
import com.codexmind.establishment.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.dto.OrderDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveOrder {

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;


    private final  EstablishmentRepository establishmentRepository;

    private final ProductRepository productRepository;

    public Order execute(Integer idEstablishment){
        var userSS = UserService.authenticated();
        assert userSS != null;
        Order order = null;
        var orderFinded = orderRepository.getOpenedCommandByUserAndEstablishment(userSS.getId(), idEstablishment);
        Customer customer = customerRepository.findById(userSS.getId())
                .orElseThrow(() -> new EntityNotFoundException("usuario nao e do tipo cliente"));
        Establishment establishment = establishmentRepository.findById(idEstablishment)
                .orElseGet(() -> Establishment.builder().id(idEstablishment).build());
        if(orderFinded == null){
            order = new Order();

            order.setCustomer(customer);
            order.setEstablishment(establishment);
            order.setOpenInstant(LocalDateTime.now());
            order.setStatus(StatusComanda.OPENED);

            return orderRepository.save(order);
        }else{
          return orderFinded;
        }
    }
}

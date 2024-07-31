package com.codexmind.establishment.usecases.order;

import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.*;
import com.codexmind.establishment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

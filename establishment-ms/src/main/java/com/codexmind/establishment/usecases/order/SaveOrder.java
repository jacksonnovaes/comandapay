package com.codexmind.establishment.usecases.order;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.codexmind.establishment.domain.enums.Profile;
import com.codexmind.establishment.repository.*;
import com.codexmind.establishment.service.UserService;
import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.PaymentWithCreditCard;
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


    private final ProductRepository productRepository;

    public Order execute(OrderDTO orderDTO){
        var userSS = UserService.authenticated();

        var order = new Order();
        order.setId(null);
        order.setAddress(Address.builder().id(orderDTO.getAddressId()).build());
        order.setInstante(LocalDateTime.now());
        if(userSS.hasRole(Profile.CLIENT)){
            order.setCustomer(customerRepository.findById(userSS.getId()).get());
        }else if (userSS.hasRole(Profile.ADMIN) || userSS.hasRole(Profile.EMPLOYEE)){
            order.setEmployee(employeeRepository.findById(userSS.getId()).get());
            order.setCustomer(customerRepository.findById(orderDTO.getCustomerId()).get());
        }

        order.setPayment(new PaymentWithCreditCard(null, PaymentStatus.PENDING.getCod(), order, 1));
        order.getPayment().setPaymentStatus(PaymentStatus.PENDING);

        order.getPayment().setOrder(order);

        paymentRepository.save(order.getPayment());

        var products = orderDTO.getProductIds().stream()
        .map(productRepository::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
        order.getProducts().addAll(products);
        orderRepository.save(order);
        productRepository.saveAll(products);
        return order;
    }
}

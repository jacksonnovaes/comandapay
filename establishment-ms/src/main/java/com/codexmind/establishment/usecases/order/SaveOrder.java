package com.codexmind.establishment.usecases.order;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.PaymentWithCreditCard;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.dto.OrderDTO;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.repository.PaymentRepository;
import com.codexmind.establishment.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveOrder {

    private final CustomerRepository customerRepository;

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;


    private final ProductRepository productRepository;

    public Order execute(OrderDTO orderDTO){

        var order = new Order();
        order.setId(null);
        order.setAddress(Address.builder().id(orderDTO.getAddressId()).build());
        order.setInstante(LocalDateTime.now());
        order.setCustomer(customerRepository.findById(orderDTO.getCustomerId()).get());
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

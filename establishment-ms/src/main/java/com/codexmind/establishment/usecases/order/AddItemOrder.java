package com.codexmind.establishment.usecases.order;

import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddItemOrder {


    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public Order execute(Integer orderId, Integer productId){
        var orderFind = orderRepository.findById(orderId);

        if (orderFind.isPresent()){
            orderFind.get().getProducts().add(productRepository.findById(productId).get());
        }else{

            throw new EntityNotFoundException("Comanda nao encontrada!");
        }

        orderRepository.save(orderFind.get());
        return orderFind.get();

    }
}

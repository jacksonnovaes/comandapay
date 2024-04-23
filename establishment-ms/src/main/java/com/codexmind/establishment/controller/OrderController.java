package com.codexmind.establishment.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.dto.OrderDTO;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.usecases.order.AddItemOrder;
import com.codexmind.establishment.usecases.order.SaveOrder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@RestController
@RequestMapping({"api/v1/order"})
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class OrderController {

    private final SaveOrder saveOrder;

    private final OrderRepository orderRepository;

    private final AddItemOrder addItemOrder;

    public OrderController(SaveOrder saveOrder, OrderRepository orderRepository, AddItemOrder addItemOrder) {
        this.saveOrder = saveOrder;
        this.orderRepository = orderRepository;
        this.addItemOrder = addItemOrder;
    }

    @PostMapping("/open")
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO orderDTO){
        var obj = saveOrder.execute(orderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{orderId}/add/")
    public ResponseEntity<Order> addItem(@PathVariable Long orderId, @RequestBody Long idProduct) {

        var orderUpdated = addItemOrder.execute(orderId, idProduct);

        return ResponseEntity.ok((orderUpdated));
    }
}

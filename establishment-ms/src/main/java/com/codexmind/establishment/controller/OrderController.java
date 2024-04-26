package com.codexmind.establishment.controller;

import java.net.URI;

import com.codexmind.establishment.converters.OrderResponseConverter;
import com.codexmind.establishment.converters.ProductConverter;
import com.codexmind.establishment.dto.OrderResponseDTO;
import com.codexmind.establishment.dto.ProductDTO;
import com.codexmind.establishment.usecases.order.GetAllOrdersByUser;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    private final GetAllOrdersByUser getAllOrdersByUser;

    private final AddItemOrder addItemOrder;

    public OrderController(SaveOrder saveOrder, GetAllOrdersByUser getAllOrdersByUser, AddItemOrder addItemOrder) {
        this.saveOrder = saveOrder;
        this.getAllOrdersByUser = getAllOrdersByUser;
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

    @GetMapping(value = "/{id}/list")
    public ResponseEntity<Page<OrderResponseDTO>> getAllProducts
            (@PathVariable Long id,
             @RequestParam(value = "page",defaultValue = "0") Integer page,
             @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPge,
             @RequestParam(value = "order",defaultValue = "id")String orderBy,
             @RequestParam(value = "direction",defaultValue = "ASC")String direction) {
        var list = getAllOrdersByUser.execute(
                id,
                page,
                linesPerPge,
                orderBy,
                direction
        ).map(OrderResponseConverter::toDTO);
        return ResponseEntity.ok(list);

    }
}

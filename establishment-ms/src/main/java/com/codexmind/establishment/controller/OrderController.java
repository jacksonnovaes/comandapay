package com.codexmind.establishment.controller;

import com.codexmind.establishment.converters.OrderResponseConverter;
import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.dto.ItemOrderRequestDTO;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.dto.OrderResponseDTO;
import com.codexmind.establishment.usecases.ItemOrder.GetItemOrder;
import com.codexmind.establishment.usecases.order.mobile.AddItemOrder;
import com.codexmind.establishment.usecases.order.mobile.CountOrders;
import com.codexmind.establishment.usecases.order.mobile.GetAllOrdersByUser;
import com.codexmind.establishment.usecases.order.SaveOrder;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping({"api/v1/order"})
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class OrderController {

    private final SaveOrder saveOrder;

    private final GetAllOrdersByUser getAllOrdersByUser;

    private final AddItemOrder addItemOrder;

    private final CountOrders countOrders;

    private final GetItemOrder getItemOrder;

    public OrderController(SaveOrder saveOrder, GetAllOrdersByUser getAllOrdersByUser, AddItemOrder addItemOrder, CountOrders countOrders, GetItemOrder getItemOrder) {
        this.saveOrder = saveOrder;
        this.getAllOrdersByUser = getAllOrdersByUser;
        this.addItemOrder = addItemOrder;
        this.countOrders = countOrders;
        this.getItemOrder = getItemOrder;
    }

    @PostMapping("/open/{id}")
    public ResponseEntity<OrderResponseDTO> insert(@PathVariable Integer id){
        var obj = saveOrder.execute(id);

        return ResponseEntity.ok(OrderResponseConverter.toDTO(obj));
    }
    @PostMapping("/admin/open")
    public ResponseEntity<Integer> openByEstab(@PathVariable Integer idEstablishment){
        var obj = saveOrder.execute(idEstablishment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{orderId}/add/")
    public ResponseEntity<Order> addItem(@PathVariable Integer orderId, @RequestBody List<ItemOrderRequestDTO> itemOrders) {

        var orderUpdated = addItemOrder.execute(orderId, itemOrders);

        return ResponseEntity.ok((orderUpdated));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<OrderResponseDTO>> getAllProducts
            (
             @RequestParam(value = "page",defaultValue = "0") Integer page,
             @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPge,
             @RequestParam(value = "orderBy",defaultValue = "id")String orderBy,
             @RequestParam(value = "direction",defaultValue = "ASC")String direction) {
        var list = getAllOrdersByUser.execute(
                page,
                linesPerPge,
                orderBy,
                direction
        ).map(OrderResponseConverter::toDTO);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/countOrders/{id}")
    public int getCountOrders(@PathVariable Integer id){
        return countOrders.execute(id);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Set<ItemOrderResponseDTO>> getItens(@PathVariable Integer id){
        var items = getItemOrder.execute(id);
        return ResponseEntity.ok(items);
    }
}


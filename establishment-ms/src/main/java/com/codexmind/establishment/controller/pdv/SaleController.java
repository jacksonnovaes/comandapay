package com.codexmind.establishment.controller.pdv;

import com.codexmind.establishment.converters.OrderResponseConverter;
import com.codexmind.establishment.dto.ItemOrderRequestDTO;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.dto.OrderResponseDTO;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.usecases.order.pdv.GetItemOrderPdv;
import com.codexmind.establishment.usecases.order.pdv.SaveOrderPdv;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/pdv")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class SaleController {

    private final OrderRepository orderRepository;
    private final SaveOrderPdv saveOrderPdv;

    private final GetItemOrderPdv getItemOrder;
    @PostMapping("/open/")
    public OrderResponseDTO openOrderAndAddItem(@RequestBody List<ItemOrderRequestDTO> items){
        return OrderResponseConverter.toDTO(saveOrderPdv.execute(items));
    }

    @GetMapping("/items/{orderId}")
    public ResponseEntity<Set<ItemOrderResponseDTO>> getItens(@PathVariable Integer orderId){
        var items = getItemOrder.execute(orderId);
        return ResponseEntity.ok(items);
    }
}

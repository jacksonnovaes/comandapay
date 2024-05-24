package com.codexmind.establishment.controller;

import com.codexmind.establishment.converters.ItemOrderConverter;
import com.codexmind.establishment.dto.ItemOrderResponseDTO;
import com.codexmind.establishment.usecases.ItemOrder.EditItemOrder;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/itemOrder")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class ItemOrderController {

    private final EditItemOrder editItemOrder;

    @PatchMapping("/edit/{id}")
    public ResponseEntity<ItemOrderResponseDTO>editItem(@PathVariable Integer id,@RequestParam int quantity){
        var itemOrder = editItemOrder.execute(id, quantity);
        return ResponseEntity.ok(ItemOrderConverter.toDTO(itemOrder));
    }
}

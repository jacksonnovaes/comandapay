package com.codexmind.establishment.controller;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.usecases.menu.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.MenuConverter;
import com.codexmind.establishment.dto.MenuDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
@RequestMapping({"api/v1/menu"})
public class MenuController {

    private final SaveMenu saveMenu;

    private final UpdateMenu updateMenu;

    private final DeleteMenu deleteMenu;

    private final DetailMenu detailMenu;

    private final ListMenuByEstablishment listMenuByEstablishment;

    public MenuController(SaveMenu saveMenu, UpdateMenu updateMenu, DeleteMenu deleteMenu, DetailMenu detailMenu, ListMenuByEstablishment listMenuByEstablishment) {
        this.saveMenu = saveMenu;
        this.updateMenu = updateMenu;
        this.deleteMenu = deleteMenu;
        this.detailMenu = detailMenu;
        this.listMenuByEstablishment = listMenuByEstablishment;
    }


    @PostMapping("/save")
    public ResponseEntity<MenuDTO> save(MenuDTO saveMenuDTO, UriComponentsBuilder uriBuilder){
        var menu = saveMenu.execute(saveMenuDTO);
        var uri = uriBuilder.path("/save/{id}").buildAndExpand(menu.getId()).toUri();
        return  ResponseEntity.created(uri).body(MenuConverter.toDTO(menu));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MenuDTO> update  (
            @PathVariable Integer id,
            @RequestBody MenuDTO menuDTO,
            UriComponentsBuilder uriBuilder){

        var menu = updateMenu.execute(id,menuDTO);
        var uri = uriBuilder.path("/update/{id}").buildAndExpand(menu.getId()).toUri();
        return  ResponseEntity.ok().body(MenuConverter.toDTO(menu));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Integer id){
        deleteMenu.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> detailMenu(@PathVariable Integer id){
        return ResponseEntity.ok().body(MenuConverter.toDTO(detailMenu.execute(id)));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<MenuDTO>> getListMenu(@PathVariable Integer id){
        List<Menu> menus = listMenuByEstablishment.execute(id);
        List<MenuDTO> menuDTOs = menus.stream()
                .map(MenuConverter::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(menuDTOs);
    }
}

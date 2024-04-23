package com.codexmind.establishment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.MenuConverter;
import com.codexmind.establishment.dto.MenuDTO;
import com.codexmind.establishment.usecases.menu.DeleteMenu;
import com.codexmind.establishment.usecases.menu.DetailMenu;
import com.codexmind.establishment.usecases.menu.SaveMenu;
import com.codexmind.establishment.usecases.menu.UpdateMenu;

@RestController

@RequestMapping({"api/v1/admin/menu", "api/v1/employee/menu"})
public class MenuController {

    private final SaveMenu saveMenu;

    private final UpdateMenu updateMenu;

    private final DeleteMenu deleteMenu;

    private final DetailMenu detailMenu;

    public MenuController(SaveMenu saveMenu, UpdateMenu updateMenu, DeleteMenu deleteMenu, DetailMenu detailMenu) {
        this.saveMenu = saveMenu;
        this.updateMenu = updateMenu;
        this.deleteMenu = deleteMenu;
        this.detailMenu = detailMenu;
    }


    @PostMapping("/save")
    public ResponseEntity<MenuDTO> save(MenuDTO saveMenuDTO, UriComponentsBuilder uriBuilder){
        var menu = saveMenu.execute(saveMenuDTO);
        var uri = uriBuilder.path("/save/{id}").buildAndExpand(menu.getId()).toUri();
        return  ResponseEntity.created(uri).body(MenuConverter.toDTO(menu));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MenuDTO> update  (
            @PathVariable Long id,
            @RequestBody MenuDTO menuDTO,
            UriComponentsBuilder uriBuilder){

        var menu = updateMenu.execute(id,menuDTO);
        var uri = uriBuilder.path("/update/{id}").buildAndExpand(menu.getId()).toUri();
        return  ResponseEntity.ok().body(MenuConverter.toDTO(menu));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Long id){
        deleteMenu.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> detailMenu(@PathVariable Long id){
        return ResponseEntity.ok().body(MenuConverter.toDTO(detailMenu.execute(id)));
    }
}

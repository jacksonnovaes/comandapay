package com.codexmind.establishment.controller;

import com.codexmind.establishment.usecases.establishment.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.EstablishmentConverter;
import com.codexmind.establishment.dto.EstablishmentDTO;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class EstablishmentController {

    private final UpdateEstablishment updateEstablishment;

    private final SaveEstablishment saveEstablishment;

    private final DetailEstablishment detailEstablishment;

    private final DeleteEstablishment deleteEstablishment;

    private final GetEstblishmentByName getEstblishmentByName;

    private final AddFavoriteEstablishment addFavoriteEstablishment;

    private final  getAllFavoritesEstablishment getAllFavoritesEstablishment;

    public EstablishmentController(UpdateEstablishment updateEstablishment, SaveEstablishment saveEstablishment, DetailEstablishment detailEstablishment, DeleteEstablishment deleteEstablishment, GetEstblishmentByName getEstblishmentByName, AddFavoriteEstablishment addFavoriteEstablishment, com.codexmind.establishment.usecases.establishment.getAllFavoritesEstablishment getAllFavoritesEstablishment) {
        this.updateEstablishment = updateEstablishment;
        this.saveEstablishment = saveEstablishment;
        this.detailEstablishment = detailEstablishment;
        this.deleteEstablishment = deleteEstablishment;
        this.getEstblishmentByName = getEstblishmentByName;
        this.addFavoriteEstablishment = addFavoriteEstablishment;
        this.getAllFavoritesEstablishment = getAllFavoritesEstablishment;
    }

    @PostMapping(value = "/admin/establishment/save")
    public ResponseEntity<EstablishmentDTO> save(
            @RequestBody EstablishmentDTO establishmentDTO,
            UriComponentsBuilder uriBuilder){
        var establishment = saveEstablishment.execute(establishmentDTO);
        var uri = uriBuilder.path("/establishment/save/{id}").buildAndExpand(establishment.getId()).toUri();
        return  ResponseEntity.created(uri).body(EstablishmentConverter.toDTO(establishment));
    }

    @PutMapping(value = "/establishment/{id}")
    public ResponseEntity<EstablishmentDTO> update  (
            @PathVariable Integer id,
            @RequestBody EstablishmentDTO updateEstablishmentDTO,
            UriComponentsBuilder uriBuilder){

        var establishment = updateEstablishment.execute(id,updateEstablishmentDTO);
        var uri = uriBuilder.path("/establishment/save/{id}").buildAndExpand(establishment.getId()).toUri();
        return  ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }

    @GetMapping(value = "/establishment/{id}")
    public ResponseEntity<EstablishmentDTO> getEstablishment(@PathVariable
                                                                 Integer id){
        var establishment = detailEstablishment.execute(id);
        return  ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }

    @GetMapping(value = "/establishment/")
    public ResponseEntity<List<EstablishmentDTO>> getEstablishment(@RequestParam
                                                             String name){
        var establishment = getEstblishmentByName.execute(name);
        return  ResponseEntity.ok().body(EstablishmentConverter.toListDTO((establishment)));
    }


    @PostMapping(value = "/establishment/favorites/add")
    public ResponseEntity<Void> addFavorites(
            @RequestBody Set<Integer> establishmentIds, Integer customerId,
            UriComponentsBuilder uriBuilder){
        addFavoriteEstablishment.execute(establishmentIds, customerId);

        return  ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/establishment/favorites/{id}")
    public ResponseEntity<List<EstablishmentDTO>> getFavorites(@PathVariable
                                                                   Integer id){
        var favorites = getAllFavoritesEstablishment.execute(id);
        return  ResponseEntity.ok().body(EstablishmentConverter.toListDTO((favorites)));
    }


    @DeleteMapping(value = "/establishment/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Integer id){
            deleteEstablishment.execute(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentDTO> detailMenu(@PathVariable Integer id){
        var establishment = detailEstablishment.execute(id)    ;
        return ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }
}

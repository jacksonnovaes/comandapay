package com.codexmind.establishment.controller;

import com.codexmind.establishment.converters.EstablishmentConverter;
import com.codexmind.establishment.dto.AddFavoriteRequest;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.dto.ResponseEstablishmentDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.usecases.establishment.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class EstablishmentController {

    private final UpdateEstablishment updateEstablishment;

    private final SaveEstablishment saveEstablishment;

    private final DetailEstablishment detailEstablishment;

    private final GetEstblishmentByName getEstblishmentByName;

    private final AddFavoriteEstablishment addFavoriteEstablishment;

    private final  getAllFavoritesEstablishment getAllFavoritesEstablishment;

    private final DeleteEstablishment deleteEstablishment;

    private final SaveImage saveImage;


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
    public ResponseEntity<ResponseEstablishmentDTO> getEstablishment(@PathVariable
                                                                 Integer id){
        var establishment = detailEstablishment.execute(id);
        return  ResponseEntity.ok().body(EstablishmentConverter.toResponseDTO(establishment));
    }
    @GetMapping(value = "/establishment/admin/{id}")
    public ResponseEntity<EstablishmentDTO> getEstablishmentEmployee(@PathVariable
                                                             Integer id){
        var establishment = detailEstablishment.execute(id);
        return  ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }

    @GetMapping(value = "/establishment/")
    public ResponseEntity<List<ResponseEstablishmentDTO>> getEstablishment(@RequestParam
                                                             String name){
        var establishment = getEstblishmentByName.execute(name);
        return  ResponseEntity.ok().body(EstablishmentConverter.toListResponseDTO((establishment)));
    }


    @PutMapping(value = "/establishment/favorites/add")
    public ResponseEntity<ResponseEstablishmentDTO> addFavorites(
            @RequestBody AddFavoriteRequest request){
                var establishment = addFavoriteEstablishment.execute(request.getEstablishmentId(), request.getCustomerId());

        return  ResponseEntity.ok().body(EstablishmentConverter.toResponseDTO(establishment));
    }

    @PostMapping("/establishment/images/{id}")
    public ResponseEntity<Void> uploadImagem(@RequestParam("file") MultipartFile file,@PathVariable Integer id) {
             URI uri = saveImage.execute(file, id);
            return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/establishment/favorites/{id}")
    public ResponseEntity<List<ResponseEstablishmentDTO>> getFavorites(@PathVariable
                                                                           Integer id){
           try {
            var favorites = getAllFavoritesEstablishment.execute(id);
            var establishmentDTOS =EstablishmentConverter.toListResponseDTO((favorites));
            return  ResponseEntity.ok().body(establishmentDTOS);
            } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

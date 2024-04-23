package com.codexmind.establishment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.EstablishmentConverter;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.usecases.establishment.DeleteEstablishment;
import com.codexmind.establishment.usecases.establishment.DetailEstablishment;
import com.codexmind.establishment.usecases.establishment.SaveEstablishment;
import com.codexmind.establishment.usecases.establishment.UpdateEstablishment;

@RestController
@RequestMapping(value = "/api/v1/admin/")

@CrossOrigin(origins = "*")
public class EstablishmentController {

    private final UpdateEstablishment updateEstablishment;

    private final SaveEstablishment saveEstablishment;

    private final DetailEstablishment detailEstablishment;

    private final DeleteEstablishment deleteEstablishment;

    public EstablishmentController(UpdateEstablishment updateEstablishment, SaveEstablishment saveEstablishment, DetailEstablishment detailEstablishment, DeleteEstablishment deleteEstablishment) {
        this.updateEstablishment = updateEstablishment;
        this.saveEstablishment = saveEstablishment;
        this.detailEstablishment = detailEstablishment;
        this.deleteEstablishment = deleteEstablishment;
    }

    @PostMapping(value = "/establishment/save")
    public ResponseEntity<EstablishmentDTO> save(
            @RequestBody EstablishmentDTO establishmentDTO,
            UriComponentsBuilder uriBuilder){
        var establishment = saveEstablishment.execute(establishmentDTO);
        var uri = uriBuilder.path("/establishment/save/{id}").buildAndExpand(establishment.getId()).toUri();
        return  ResponseEntity.created(uri).body(EstablishmentConverter.toDTO(establishment));
    }

    @PutMapping(value = "/establishment/{id}")
    public ResponseEntity<EstablishmentDTO> update  (
            @PathVariable Long id,
            @RequestBody EstablishmentDTO updateEstablishmentDTO,
            UriComponentsBuilder uriBuilder){

        var establishment = updateEstablishment.execute(id,updateEstablishmentDTO);
        var uri = uriBuilder.path("/establishment/save/{id}").buildAndExpand(establishment.getId()).toUri();
        return  ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }

    @GetMapping(value = "/establishment/{id}")
    public ResponseEntity<EstablishmentDTO> getEstablishment(@PathVariable
            Long id){
        var establishment = detailEstablishment.execute(id);
        return  ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }

    @DeleteMapping(value = "/establishment/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Long id){
            deleteEstablishment.execute(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentDTO> detailMenu(@PathVariable Long id){
        var establishment = detailEstablishment.execute(id)    ;
        return ResponseEntity.ok().body(EstablishmentConverter.toDTO(establishment));
    }
}

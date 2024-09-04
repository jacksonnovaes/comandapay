package com.codexmind.establishment.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codexmind.establishment.dto.AddressDTO;
import com.codexmind.establishment.service.ServiceClient;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(origins = "*")
public class AddressViaCepController {

    private final ServiceClient viaCepClient;

    public AddressViaCepController(ServiceClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @GetMapping(value = "/address/{cep}")
    public ResponseEntity<AddressDTO> getAddressViaCepReq(@PathVariable String cep){
        var address = viaCepClient.buscaEnderecoPorCep(cep);
        return ResponseEntity.ok(address);
    }
}

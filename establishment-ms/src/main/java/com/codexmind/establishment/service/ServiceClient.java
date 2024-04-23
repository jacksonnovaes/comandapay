package com.codexmind.establishment.service;

import com.codexmind.establishment.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ServiceClient {

    @GetMapping("{cep}/json")
    AddressDTO buscaEnderecoPorCep(@PathVariable("cep") String cep);
}
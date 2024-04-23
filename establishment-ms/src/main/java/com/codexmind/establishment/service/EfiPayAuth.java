package com.codexmind.establishment.service;


import com.codexmind.establishment.dto.AuthorizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "efiAuth", url = "http://localhost:8081")
public interface EfiPayAuth {

    @PostMapping("/cobv/auth")
    AuthorizationDTO getAuth();
}

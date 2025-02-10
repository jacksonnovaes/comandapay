package com.codexmind.establishment.service.EFI;


import com.codexmind.establishment.dto.AuthorizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "efiAuth", url = "${efi.cob.url}")
public interface EfiPayAuth {

    @PostMapping("/cobv/auth")
    AuthorizationDTO getAuth();
}

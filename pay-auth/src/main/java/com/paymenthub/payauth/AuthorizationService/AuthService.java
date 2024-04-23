package com.paymenthub.payauth.AuthorizationService;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "efiAuth", url = "https://api-pix-h.gerencianet.com.br")
public interface AuthService {




}

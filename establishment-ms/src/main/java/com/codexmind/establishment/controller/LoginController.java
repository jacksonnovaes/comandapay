package com.codexmind.establishment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codexmind.establishment.domain.User;
import com.codexmind.establishment.dto.LoginDTO;
import com.codexmind.establishment.security.TokenJWT;
import com.codexmind.establishment.security.TokenService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    public LoginController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid LoginDTO loginDTO){
        var token = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPass());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.generateWebToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(tokenJwt));

    }

}


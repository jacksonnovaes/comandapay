package com.paymenthub.payauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymenthub.payauth.dto.AuthorizationDTO;
import com.paymenthub.payauth.useCases.AuthEfiPay;


@RestController
@RequestMapping
public class AuthEfiPayController {

	@Autowired
   private AuthEfiPay authEfiPay;

	@PostMapping("/cobv/auth")
	public ResponseEntity<AuthorizationDTO> authorizing() {
		
		return ResponseEntity.ok(authEfiPay.execute());
	}
	

}

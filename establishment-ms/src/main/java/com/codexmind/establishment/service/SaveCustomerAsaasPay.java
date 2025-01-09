package com.codexmind.establishment.service;

import com.codexmind.establishment.dto.asaas.RequestSaveCustomerAsaasDTO;
import com.codexmind.establishment.dto.asaas.ResponseSaveCustomerAsaasDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Service
public class SaveCustomerAsaasPay {

    private final RestTemplate restTemplate;

    @Value("${asaas.api.url}") // Defina no application.properties ou application.yml
    private String asaasApiUrl;

    @Value("${asaas.api.token}") // Guarde o token de forma segura
    private String asaasApiToken;

    public SaveCustomerAsaasPay(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }

    public ResponseSaveCustomerAsaasDTO createCustomer(RequestSaveCustomerAsaasDTO customerDTO) {
        // Construir cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("access_token", asaasApiToken);

        // Corpo da requisição
        HttpEntity<RequestSaveCustomerAsaasDTO> requestEntity = new HttpEntity<>(customerDTO, headers);

        try {
            // Fazer requisição POST
            ResponseEntity<ResponseSaveCustomerAsaasDTO> response = restTemplate.postForEntity(
                    asaasApiUrl + "/api/v3/customers",
                    requestEntity,
                    ResponseSaveCustomerAsaasDTO.class
            );

            // Verificar resposta
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Erro ao salvar cliente: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente no Asaas.", e);
        }
    }
}


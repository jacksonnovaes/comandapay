package com.codexmind.establishment.service.asaas;

import com.codexmind.establishment.dto.asaas.RequestSaveCustomerAsaasDTO;
import com.codexmind.establishment.dto.asaas.ResponseSaveCustomerAsaasDTO;

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

    @Value("${asaas.api.url}")
    private String asaasApiUrl;

    @Value("${asaas.api.token}")
    private String asaasApiToken;

    @Value("${asaas.api.path}")
    private String customerPath;

    public SaveCustomerAsaasPay(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }

    public ResponseSaveCustomerAsaasDTO createCustomer(RequestSaveCustomerAsaasDTO customerDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("access_token", asaasApiToken);

        HttpEntity<RequestSaveCustomerAsaasDTO> requestEntity = new HttpEntity<>(customerDTO, headers);

        try {
            ResponseEntity<ResponseSaveCustomerAsaasDTO> response = restTemplate.postForEntity(
                    asaasApiUrl + customerPath,
                    requestEntity,
                    ResponseSaveCustomerAsaasDTO.class
            );

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


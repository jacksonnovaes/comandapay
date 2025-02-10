package com.codexmind.establishment.usecases.payment.impl;

import com.codexmind.establishment.dto.asaas.PaymentResponseDTO;
import com.codexmind.establishment.dto.asaas.RequestPixTransactonDTO;
import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.usecases.payment.DoPaymentAsaas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Service
public class DoPaymentAsaasImpl implements DoPaymentAsaas {

    @Value("${asaas.api.url}")
    private String asaasApiUrl;

    @Value("${asaas.api.token}")
    private String asaasApiToken;

    @Value("${asaas.api.payments}")
    private String asaasPath;

    private final RestTemplate restTemplate;

    public DoPaymentAsaasImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Override
    public PaymentResponseDTO pixPayment(RequestPixTransactonDTO transactionDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("access_token", asaasApiToken);

        HttpEntity<RequestPixTransactonDTO> requestEntity = new HttpEntity<>(transactionDTO, headers);
        try {
            ResponseEntity<PaymentResponseDTO> response = restTemplate.postForEntity(
                    asaasApiUrl + asaasPath,
                    requestEntity,
                    PaymentResponseDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new AuthorizationException("Erro ao criar cobrancae: " + response.getStatusCode());
            }if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new AuthorizationException("Erro ao criar cobrancae: " + response.getStatusCode());
            } else {
                throw new RuntimeException("Erro ao salvar cliente: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente no Asaas.", e);
        }
    }
}

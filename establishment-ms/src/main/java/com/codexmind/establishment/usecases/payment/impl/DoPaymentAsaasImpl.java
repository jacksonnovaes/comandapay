package com.codexmind.establishment.usecases.payment.impl;

import com.codexmind.establishment.dto.asaas.*;
import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.service.UserService;
import com.codexmind.establishment.usecases.customer.DetailCustomer;
import com.codexmind.establishment.usecases.payment.DoPaymentAsaas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class DoPaymentAsaasImpl implements DoPaymentAsaas {

    @Value("${asaas.api.url}")
    private String asaasApiUrl;

    @Value("${asaas.api.token}")
    private String asaasApiToken;

    @Value("${asaas.api.payments}")
    private String asaasPath;

    @Value("${asaas.api.qrCode}")
    private String qrCodePath;

    private final DetailCustomer detailCustomer;

    private final RestTemplate restTemplate;

    public DoPaymentAsaasImpl(DetailCustomer detailCustomer, RestTemplateBuilder restTemplateBuilder) {
        this.detailCustomer = detailCustomer;
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Override
    public PaymentResponseDTO pixPayment(TransactonDTO transactionDTO) {
        var user = UserService.authenticated();
        assert user != null;
        var userDetail = detailCustomer.execute(user.getId());
        var customer  = new RequestPixTransactonDTO(
                userDetail.getCustomerId(),
                BillingType.PIX,
                transactionDTO.value(),
                LocalDateTime.now(),
                transactionDTO.description()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("access_token", asaasApiToken);

        HttpEntity<RequestPixTransactonDTO> requestEntity = new HttpEntity<>(customer, headers);
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

    @Override
    public QrCodeDTO getQrCode(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("access_token", asaasApiToken);


        String url = UriComponentsBuilder.fromHttpUrl(asaasApiUrl)
                .path(asaasPath+qrCodePath)
                .buildAndExpand(id)
                .toUriString();

        try {
            ResponseEntity<QrCodeDTO> response = restTemplate.postForEntity(
                    url,
                    new HttpEntity<>(headers),
                    QrCodeDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new AuthorizationException("Erro ao obter  qrCode " + response.getStatusCode());
            }if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new AuthorizationException("Erro ao obter  qrCode: " + response.getStatusCode());
            } else {
                throw new RuntimeException("Erro ao obter  qrCode " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter  qrCode no Asaas.", e);
        }
    }
}

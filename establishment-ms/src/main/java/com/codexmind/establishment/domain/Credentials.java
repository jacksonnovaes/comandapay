package com.codexmind.establishment.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Credentials {

    @Value("${api.credentials.client_id}")
    private String clientId;
    @Value("${api.credentials.client_secret}")
    private String clientSecret;
    @Value("${api.credentials.certificate}")
    private String certificate;
    @Value("${api.credentials.sandbox}")
    private boolean sandbox;
    @Value("${api.credentials.debug}")
    private boolean debug;


    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCertificate() {
        return certificate;
    }

    public boolean isSandbox() {
        return sandbox;
    }

    public boolean isDebug() {
        return debug;
    }

}

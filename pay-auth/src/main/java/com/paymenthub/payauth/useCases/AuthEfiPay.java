package com.paymenthub.payauth.useCases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymenthub.payauth.dto.AuthorizationDTO;

@Service
public class AuthEfiPay {
    

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("deprecation")
    public AuthorizationDTO execute(){
        String client_id = "Client_Id_e3c50f7c2146dc9318dbca38d6aec71d1da6fde7";
		String client_secret = "Client_Secret_dbc6b397dedca80d95366524b740e3ba42e6cad4";;
		String basicAuth = Base64.getEncoder().encodeToString(((client_id+':'+client_secret).getBytes()));
	  
		//Diretório em que seu certificado em formato .p12 deve ser inserido
		System.setProperty("javax.net.ssl.keyStore", "./certs/hom-app-comanda.p12"); 
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		URL url;
		try {
			url = new URL ("https://pix-h.api.efipay.com.br/oauth/token");
		//Para ambiente de Desenvolvimento              
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Basic "+ basicAuth);
		conn.setSSLSocketFactory(sslsocketfactory);
		String input = "{\"grant_type\": \"client_credentials\"}";
		
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();     
	
		InputStreamReader reader = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(reader);

        StringBuilder response = new StringBuilder();


        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        conn.disconnect();
	
        String jsonResponse = response.toString();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        String accessToken = rootNode.get("access_token").asText();
        String tokenType = rootNode.get("token_type").asText();
        int expires = rootNode.get("expires_in").asInt();
        String scope = rootNode.get("scope").asText();

        // Create and return AuthorizationDTO object
        return new AuthorizationDTO(accessToken, tokenType, expires, scope);
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	
    }
}

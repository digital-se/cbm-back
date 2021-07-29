package com.digitalse.cbm.back.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class KeycloakUtils {
    @Value("keycloak.auth-server-url")
    private String auth_server_url;
    @Value("keycloak.realm")
    private String realm;

    public Boolean isAuthorized(String token){
        String url = "https://"+auth_server_url+"/realms/"+realm+"/protocol/openid-connect/userinfo";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);

        ResponseEntity<String> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(headers),
        String.class
        );
    
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}

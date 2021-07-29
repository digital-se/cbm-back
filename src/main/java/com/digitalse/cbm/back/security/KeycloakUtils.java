package com.digitalse.cbm.back.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

public class KeycloakUtils {
    @Value("keycloak.auth-server-url")
    private String auth_server_url;
    @Value("keycloak.realm")
    private String realm;

    public Boolean isAuthorized(Optional<String> token){
        try{

            if(token == null){
                return false;
            } else {
                System.out.println(token.get());
                return true;
            }

            /* String url = "https://"+auth_server_url+"/realms/"+realm+"/protocol/openid-connect/userinfo";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
    
            headers.setBearerAuth(token);
    
            ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class
            );
        
            return response.getStatusCode().equals(HttpStatus.OK); */
        } catch (NullPointerException e) {
            return false;
        } catch (Exception e) {
            //TODO: Tratar exceção melhor
            return false;
        }
    }
}

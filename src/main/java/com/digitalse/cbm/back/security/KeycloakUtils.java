package com.digitalse.cbm.back.security;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUtils {
    @Value("keycloak.auth-server-url")
    private String auth_server_url;
    @Value("keycloak.realm")
    private String realm;

    public void tokenAnalyzer() {
        String token = "";
        try{
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();
        
        
            if (principal instanceof KeycloakPrincipal) {
                @SuppressWarnings("unchecked")
                KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                token = kPrincipal.getKeycloakSecurityContext().getToken().getRealmAccess().getRoles().toString();
            }
        } catch (Exception e){
            token = "{Sem token}";
        }
        

        System.out.println(token);
        /* String dob = "";
        Map<String, Object> customClaims = new HashMap<String, Object>();

        if (principal instanceof KeycloakPrincipal) {
            @SuppressWarnings("unchecked")
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getToken();

            customClaims = token.getOtherClaims();

            if (customClaims.containsKey("DOB")) {
                dob = String.valueOf(customClaims.get("DOB"));
            }
        }
        System.out.println(dob.toString()); */
    }

    public Boolean isAuthorized(Optional<String> token) {
        try {

            if (token == null) {
                return false;
            } else {
                System.out.println(token.get());
                return true;
            }

            /*
             * String url = "https://"+auth_server_url+"/realms/"+realm+
             * "/protocol/openid-connect/userinfo"; RestTemplate restTemplate = new
             * RestTemplate(); HttpHeaders headers = new HttpHeaders();
             * 
             * headers.setBearerAuth(token);
             * 
             * ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.GET,
             * new HttpEntity<>(headers), String.class );
             * 
             * return response.getStatusCode().equals(HttpStatus.OK);
             */
        } catch (NullPointerException e) {
            return false;
        } catch (Exception e) {
            // TODO: Tratar exceção melhor
            return false;
        }
    }
}

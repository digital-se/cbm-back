package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.responseFiles.RFMilitar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MilitarService {

    /**
     * Busca um militar pelo seu nome (na API do CBMSE)
     * @param nome nome do militar a ser buscado
     * @return reponse files de cada militar encontrado
     * @throws IOException
     */
    public List<RFMilitar> getListByName(String nome) throws IOException {
        String url = "https://sandbox-api.cbm.se.gov.br/bmrh/militares/obtermilitares/" + nome;
        JsonNode jsonNode = new ObjectMapper().readTree(new RestTemplate().getForObject(url, String.class));
        List<RFMilitar> listaMilitares = new ArrayList<>();
        for (JsonNode node : jsonNode.findValues("ViewMilitar")) {
            RFMilitar mdto = new RFMilitar(node.get("num_matricula").asText(), node.get("nom_completo").asText(),
                    node.get("nom_guerra").asText(), node.get("dsc_cargo").asText());
            listaMilitares.add(mdto);
        }
        return listaMilitares;
    }

    /**
     * Busca um militar pela sua matricula (na API do CBMSE)
     * @param matricula matricula do militar a ser buscado
     * @return reponse files de cada militar encontrado
     * @throws IOException
     */
    public RFMilitar getListByMatricula(String matricula) throws IOException {
        String url = "https://sandbox-api.cbm.se.gov.br/bmrh/militares/getMilitaryByMat/" + matricula;
        JsonNode jsonNode = new ObjectMapper().readTree(new RestTemplate().getForObject(url, String.class));
        System.out.println(jsonNode.toString());
        RFMilitar militarFound = new RFMilitar(jsonNode.get("num_matricula").asText(), jsonNode.get("nom_completo").asText(),
                jsonNode.get("nom_guerra").asText(), jsonNode.get("dsc_cargo").asText());
        return militarFound;
    }

}

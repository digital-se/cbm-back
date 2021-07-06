package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.MilitarDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MilitarService {

    public List<MilitarDTO> getListByName(String nome) throws IOException {
        // System.out.println(URLEncoder.encode("https://sandbox-api.cbm.se.gov.br/bmrh/militares/obtermilitares/"
        // + nome, "UTF-8"));
        String url = "https://sandbox-api.cbm.se.gov.br/bmrh/militares/obtermilitares/" + nome;
        JsonNode jsonNode = new ObjectMapper().readTree(new RestTemplate().getForObject(url, String.class));
        List<MilitarDTO> listaMilitares = new ArrayList<>();
        for (JsonNode node : jsonNode.findValues("ViewMilitar")) {
            MilitarDTO mdto = new MilitarDTO(node.get("num_matricula").asText(), node.get("nom_completo").asText(),
                    node.get("nom_guerra").asText(), node.get("dsc_cargo").asText());
            listaMilitares.add(mdto);
        }
        return listaMilitares;
    }

    public List<MilitarDTO> getListByMatricula(String matricula) throws IOException {
        // System.out.println(URLEncoder.encode("https://sandbox-api.cbm.se.gov.br/bmrh/militares/getMilitaryByMat/"
        // + nome, "UTF-8"));
        String url = "https://sandbox-api.cbm.se.gov.br/bmrh/militares/getMilitaryByMat/" + matricula;
        JsonNode jsonNode = new ObjectMapper().readTree(new RestTemplate().getForObject(url, String.class));
        System.out.println(jsonNode.toString());
        List<MilitarDTO> listaMilitares = new ArrayList<>();
        for (JsonNode node : jsonNode.findValues("ViewMilitar")) {
            MilitarDTO mdto = new MilitarDTO(node.get("num_matricula").asText(), node.get("nom_completo").asText(),
                    node.get("nom_guerra").asText(), node.get("dsc_cargo").asText());
            listaMilitares.add(mdto);
        }
        return listaMilitares;
    }

}

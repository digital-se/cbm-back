package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bombeiros.siteinterno.DTO.MilitarDTO;
import com.bombeiros.siteinterno.models.Militar;
import com.bombeiros.siteinterno.repository.MilitarRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MilitarService {

    @Autowired
    private MilitarRepository militarRepository;

    public MilitarDTO save(String numMatricula) throws IOException {
        return new MilitarDTO(militarRepository.save(new Militar(numMatricula)));
    }

    public List<MilitarDTO> getListByName(String nome) throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(new RestTemplate().getForObject("https://sandbox-api.cbm.se.gov.br/bmrh/militares/obtermilitares/" + nome, String.class));
        List<MilitarDTO> listaMilitares = new ArrayList<>();
        for (JsonNode node : jsonNode.findValues("ViewMilitar")) {
            MilitarDTO mdto = new MilitarDTO(node.get("num_matricula").asText(), node.get("nom_completo").asText(), node.get("nom_guerra").asText(), node.get("dsc_cargo").asText());
            listaMilitares.add(mdto);
        }
        return listaMilitares;
    }

    public MilitarDTO getByMatricula(String numMatricula) throws IOException {
        return new MilitarDTO(militarRepository.findByNumMatricula(numMatricula));
    }
    
    public Boolean hasMilitar(String numMatricula) throws IOException {
        return militarRepository.findByNumMatricula(numMatricula) != null;
    }
}

package com.bombeiros.back.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.bombeiros.back.DTO.MilitarDTO;
import com.bombeiros.back.models.Militar;
import com.bombeiros.back.repository.MilitarRepository;
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
        System.out.println(URLEncoder.encode("https://sandbox-api.cbm.se.gov.br/bmrh/militares/obtermilitares/" + nome, "UTF-8"));
        String url = "https://sandbox-api.cbm.se.gov.br/bmrh/militares/obtermilitares/" + nome;
        JsonNode jsonNode = new ObjectMapper().readTree(new RestTemplate().getForObject(URLEncoder.encode(url, "UTF-8"), String.class));
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

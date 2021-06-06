package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.MilitarDTO;
import com.digitalse.cbm.back.models.Militar;
import com.digitalse.cbm.back.repository.MilitarRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MilitarService {

    @Autowired
    private MilitarRepository militarRepository;

    public MilitarDTO save(String matricula) throws IOException {
        return new MilitarDTO(militarRepository.save(new Militar(matricula)));
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

    public MilitarDTO getByMatricula(String matricula) throws IOException {
        return new MilitarDTO(militarRepository.findByMatricula(matricula));
    }
    
    public Boolean hasMilitar(String matricula) throws IOException {
        return militarRepository.findByMatricula(matricula) != null;
    }
}

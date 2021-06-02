package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.DTO.MilitarDTO;
import com.bombeiros.siteinterno.services.MilitarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/militares")
public class MilitarController {
    
    @Autowired
    private MilitarService militarService;

    public MilitarController(MilitarService militarService) {
        this.militarService = militarService;
    }

    @GetMapping("/getMilitarPorNome/{nome}")
    public ResponseEntity<List<MilitarDTO>> getMilitarPorNome(@PathVariable String nome) throws IOException {
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(militarService.getListByName(nome));
        } catch(NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getMilitarPorMatricula/{matricula}")
    public ResponseEntity<MilitarDTO> getMilitarPorMatricula(@PathVariable String matricula) throws IOException {
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(militarService.getByMatricula(matricula));
        } catch(NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

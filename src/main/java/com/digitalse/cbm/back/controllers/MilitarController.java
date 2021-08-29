package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.responseFiles.RFsMilitar.RFMilitar;
import com.digitalse.cbm.back.services.MilitarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping
public class MilitarController {

    @Autowired
    private MilitarService militarService;

    public MilitarController(MilitarService militarService) {
        this.militarService = militarService;
    }

    @ApiOperation(value = "Procura militares por nome na API do CBM")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Retornou o resultado da busca na API do CBM"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/militares/query")
    public ResponseEntity<List<RFMilitar>> getMilitarPorNome(@RequestParam(required = false) String nome, @RequestParam(required = false) String matricula) throws IOException {
        try {
            List<RFMilitar> result = new ArrayList<RFMilitar>();
            if(nome != null && matricula != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas um campo na query é permitido!!!");
            if(nome != null && matricula == null) result.addAll(militarService.getListByName(nome));
            if(nome == null && matricula != null) result.add(militarService.getListByMatricula(matricula));
            
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (HttpClientErrorException e) {
            System.out.println(e);
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    /* @ApiOperation(value = "Procura militares por matricula na API do CBM")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Retornou o resultado da busca na API do CBM"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/militar/buscaPorMatricula")
    public ResponseEntity<RFMilitar> getMilitarPorMatricula() throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body();
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (HttpClientErrorException e) {
            System.out.println(e);
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    } */
}

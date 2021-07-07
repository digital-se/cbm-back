package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.util.List;

import com.digitalse.cbm.back.DTO.MilitarDTO;
import com.digitalse.cbm.back.services.MilitarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

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
    @GetMapping("/militares/{nome}")
    public ResponseEntity<List<MilitarDTO>> getMilitarPorNome(@PathVariable String nome) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(militarService.getListByName(nome));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (HttpClientErrorException e) {
            System.out.println(e);
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Procura militares por matricula na API do CBM")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Retornou o resultado da busca na API do CBM"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/militar/{matricula}")
    public ResponseEntity<MilitarDTO> getMilitarPorMatricula(@PathVariable String matricula) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(militarService.getListByMatricula(matricula));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (HttpClientErrorException e) {
            System.out.println(e);
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

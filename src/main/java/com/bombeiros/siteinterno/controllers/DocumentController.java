package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.BgaResponseFile;
import com.bombeiros.siteinterno.models.Document;
import com.bombeiros.siteinterno.services.BgaServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/bga")
public class DocumentController {

    @Autowired
    private BgaServices artigoServices;

    public DocumentController(BgaServices artigoServices) {
        this.artigoServices = artigoServices;
    }

    // SALVAR
    @ApiOperation(value = "Cria um BGA e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BGA e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/v1/salvar")
    @ResponseBody
    public ResponseEntity<Document> salvar(@RequestPart("artigo") Document artigo, @RequestPart("file") MultipartFile file)
            throws IOException {

        artigoServices.salvar(artigo, file);

        return ResponseEntity.status(HttpStatus.CREATED).body(artigo);
    }

    // LISTAR DOCUMENTOS
    @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BGA")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo BGA"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/v1/documentos/{bgaid}")
    public ResponseEntity<List<ArtigoResponseFile>> listarDocumentos(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getDocumentos(id));
    }

    // LISTAR ARTIGOS
    @ApiOperation(value = "Retorna uma lista de BGA's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de BGA's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/v1/artigos")
    public ResponseEntity<List<BgaResponseFile>> listarArtigos() { //adicionar parametro string para query futuramente
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getArtigos());
    }

    // LISTAR TUDO | PARA TESTES, REMOVER FUTURAMENTE
    @GetMapping("/v1/tudo")
    public ResponseEntity<List<ArtigoResponseFile>> listarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getTudo());

    }

}

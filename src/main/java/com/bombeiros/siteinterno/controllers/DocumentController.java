package com.bombeiros.siteinterno.controllers;

import java.io.IOException;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.services.DocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/bga")
public class DocumentController {

    @Autowired
    private DocumentoService documentoService;

    public DocumentController(DocumentoService documentoService) {
    this.documentoService = documentoService;
    }

    // SALVAR
    @ApiOperation(value = "Cria um BGA e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BGA e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/criar")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> criar(@RequestPart("documento") DocumentoDTO documentoDTO) throws IOException {

        documentoService.criar(documentoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(documentoDTO);
    }

    // // LISTAR ARQUIVOS
    // @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BGA")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma
    // lista de documentos de um respectivo BGA"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @GetMapping("/v1/documentos/{bgaid}")
    // public ResponseEntity<List<ArquivoDTO>> listarDocumentos(@PathVariable long
    // id) {
    // return
    // ResponseEntity.status(HttpStatus.OK).body(documentService.getDocumentos(id));
    // }

    // // LISTAR DOCUMENTOS
    // @ApiOperation(value = "Retorna uma lista de BGA's")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma
    // lista de BGA's"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @GetMapping("/v1/artigos")
    // public ResponseEntity<List<DocumentoDTO>> listarArtigos() { //adicionar
    // parametro string para query futuramente
    // return
    // ResponseEntity.status(HttpStatus.OK).body(documentService.getArtigos());
    // }

    // // LISTAR TUDO | PARA TESTES, REMOVER FUTURAMENTE
    // @GetMapping("/v1/tudo")
    // public ResponseEntity<List<DocumentoDTO>> listarTudo() {
    // return ResponseEntity.status(HttpStatus.OK).body(documentService.getTudo());

    // }

}

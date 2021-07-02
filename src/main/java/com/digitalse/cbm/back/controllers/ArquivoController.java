package com.digitalse.cbm.back.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.services.ArquivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @ApiOperation(value = "Adiciona um ou mais arquivos a um documento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adicionou um ou mais arquivos a um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping(value = "/documentos/{documento_id}/arquivos", consumes = { "multipart/form-data" })
    @ResponseBody
    public ResponseEntity<List<ArquivoDTO>> cadastrar(@PathVariable(required = true) long documento_id,
            @RequestPart(required = true) List<ArquivoDTO> arquivosDTO,
            @RequestPart(required = true) List<MultipartFile> files) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(arquivoService.addAllArchives(documento_id,
                    new LinkedList<>(arquivosDTO), new LinkedList<>(files)));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Lista os arquivos de um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Listou os arquivos de um documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{documento_id}/arquivos")
    @ResponseBody
    public ResponseEntity<List<ArquivoDTO>> listar(@PathVariable long documento_id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(arquivoService.getArchivesFromDocument(documento_id));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Retorna um arquivo especifico de um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Retornou um arquivo de um documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{documento_id}/arquivos/{arquivo_id}")
    @ResponseBody
    public ResponseEntity<ArquivoDTO> obter(@PathVariable long arquivo_id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(arquivoService.findArchive(arquivo_id));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiOperation(value = "Atualiza um arquivo de um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Atualizou um arquivo"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PutMapping("/documentos/{documento_id}/arquivos/{arquivo_id}")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> atualizar(@PathVariable long arquivo_id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Retorna um InputStreamResource de um arquivo")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Retornou um InputStreamResource"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{documento_id}/arquivos/{arquivo_id}/arquivo")
    @ResponseBody
    public ResponseEntity<InputStreamResource> dadosOcr(@PathVariable long arquivo_id) throws IOException {
        try {
            ArquivoDTO arq = arquivoService.getFile(arquivo_id);
            return ResponseEntity.status(HttpStatus.FOUND).contentLength(arq.getTamanho())
                    .contentType(org.springframework.http.MediaType.parseMediaType(arq.getMime()))
                    .body(new InputStreamResource(new ByteArrayInputStream(arq.getDados())));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

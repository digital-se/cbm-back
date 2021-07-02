package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.mappers.ArquivoMapper;
import com.digitalse.cbm.back.services.ArquivoService;
import com.digitalse.cbm.back.services.DocumentoService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DocumentoService documentoService;

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private ArquivoMapper mapperArq = Mappers.getMapper(ArquivoMapper.class);

    @ApiOperation(value = "Adiciona um ou mais arquivos a um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Adicionou um ou mais arquivos a um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping(value = "/documentos/{documento_id}/arquivos", consumes = { "multipart/form-data" })
    @ResponseBody
    public ResponseEntity<List<ArquivoDTO>> cadastrar(@PathVariable(required = true) long documento_id,
            @RequestPart(required = true) List<ArquivoDTO> arquivosDTO, @RequestPart(required = true) List<MultipartFile> files) throws IOException {
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
    @GetMapping("/documentos/{id}/arquivos")
    @ResponseBody
    public ResponseEntity<List<ArquivoDTO>> listar(@PathVariable long id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapperArq.toDTO(documentoService.getArquivosDeDocumento(id)));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/documentos/{id}/arquivos/{id}")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> obter(@PathVariable long id) throws IOException {
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

    @PutMapping("/documentos/{id}/arquivos/{id}")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> atualizar(@PathVariable long id) throws IOException {
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

    @GetMapping("/documentos/{id}/arquivos/{id}/arquivo")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> dadosOcr(@PathVariable long id) throws IOException {
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
}

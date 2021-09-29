package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import com.digitalse.cbm.back.DTO.DTOsDocumento.DocumentoDTO;
import com.digitalse.cbm.back.responseFiles.RFsDocumento.RFBuscaDocumentos;
import com.digitalse.cbm.back.responseFiles.RFsDocumento.RFDocumento;
import com.digitalse.cbm.back.responseFiles.RFsDocumento.RFEditarDocumento;
import com.digitalse.cbm.back.services.DocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @ApiOperation(value = "Cria um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping(path = "/documentos")
    @ResponseBody
    public ResponseEntity<RFDocumento> createDocument(@RequestBody DocumentoDTO documentoDTO) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.addDocument(documentoDTO));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
        }
    }

    @ApiOperation(value = "Busca documentos por campos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Buscou documentos e retornou os que se encaixam"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    @ResponseBody
    public ResponseEntity<List<RFBuscaDocumentos>> listDocument(@RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo, @RequestParam(required = false) String numeracao,
            @RequestParam(required = false) LocalDate dataInicial, @RequestParam(required = false) LocalDate dataFinal,
            @RequestParam(required = false) String matriculaMilitar, @RequestParam(required = false) String nomeMilitar,
            @RequestParam(required = false) String palavras) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentoService.getDocument(nome, tipo, numeracao, dataInicial, dataFinal, matriculaMilitar, nomeMilitar, palavras));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Busca um documento pelo id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Busca um documento e retornou o mesmo"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<RFDocumento> getDocument(@PathVariable long id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentoService.getDocumento(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Atualiza um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Atualizou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PutMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<RFEditarDocumento> updateDocument(@RequestBody DocumentoDTO documentoNovo) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentoService.updateDocument(documentoNovo));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    /* @ApiOperation(value = "Deleta (oculta) um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Deletou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @DeleteMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<RFEditarDocumento> deleteDocument(@PathVariable long id,
            @RequestBody DocumentoDTO documentoNovo) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    } */

}

package com.digitalse.cbm.back.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoDTO;
import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoEditarDTO;
import com.digitalse.cbm.back.responseFiles.RFsArquivo.RFArquivo;
import com.digitalse.cbm.back.responseFiles.RFsArquivo.RFCriarArquivo;
import com.digitalse.cbm.back.responseFiles.RFsBucket.RFBucket;
import com.digitalse.cbm.back.services.ArquivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v2")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @ApiOperation(value = "Adiciona um arquivo a um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Adicionou um arquivo a um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping(value = "/documentos/{documento_id}/arquivos")
    @ResponseBody
    public ResponseEntity<RFCriarArquivo> addFile(@PathVariable(required = true) long documento_id,
            @RequestPart(required = true) ArquivoDTO arquivoDTO, @RequestPart(required = true) MultipartFile file)
            throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(arquivoService.addFile(documento_id, arquivoDTO, file));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Lista os arquivos de um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Listou os arquivos de um documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{documento_id}/arquivos")
    @ResponseBody
    public ResponseEntity<List<RFArquivo>> listFile(@PathVariable long documento_id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(arquivoService.listFile(documento_id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Retorna informações sobre um arquivo especifico de um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou um arquivo de um documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{documento_id}/arquivos/{arquivo_id}")
    @ResponseBody
    public ResponseEntity<RFArquivo> getFile(@PathVariable long arquivo_id /* Exploit? */) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(arquivoService.getFile(arquivo_id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Atualiza um arquivo de um documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Atualizou um arquivo"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PatchMapping("/documentos/{documento_id}/arquivos/{arquivo_id}")
    @ResponseBody
    public ResponseEntity<RFArquivo> updateFile(@PathVariable(name = "documento_id") long documento_id, @RequestBody ArquivoEditarDTO arquivodto) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(arquivoService.updateFile(documento_id, arquivodto));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Deleta um arquivo pelo seu ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Deletou um arquivo com sucesso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @DeleteMapping("/documentos/{documento_id}/arquivos/{arquivo_id}")
    @ResponseBody
    public ResponseEntity<String> deleteFile(@PathVariable long arquivo_id) throws IOException {
        try {
            arquivoService.deleteFile(arquivo_id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Retorna um InputStreamResource de um arquivo")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou um InputStreamResource"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{documento_id}/arquivos/{arquivo_id}/arquivo")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFileRaw(@PathVariable long arquivo_id)
            throws IOException {
        try {
            RFBucket rfbucket = arquivoService.getFileRaw(arquivo_id);
            return ResponseEntity.status(HttpStatus.OK).contentLength(rfbucket.getTamanho())
                    .contentType(org.springframework.http.MediaType.parseMediaType(rfbucket.getMime()))
                    .body(new InputStreamResource(new ByteArrayInputStream(rfbucket.getDados())));

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    
}

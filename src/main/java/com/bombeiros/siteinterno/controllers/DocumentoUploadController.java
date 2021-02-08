package com.bombeiros.siteinterno.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.ResponseMessage;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/documentos")
public class DocumentoUploadController {
    
    @Autowired
    private FileStorageService storageService;



    @ApiOperation(value = "Faz o upload de documento ")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Fez o upload de documento"),
		@ApiResponse(code = 401, message = "Sem autenticação"),
		@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		@ApiResponse(code = 404, message = "Não encontrado"),
		@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
    @PostMapping
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.salvarDocumento(file);

            message = "Upload de documento com sucesso: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "Não foi possível dar upload na documento: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    
    @ApiOperation(value = "Retorna a lista de documentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou a lista de documentos"),
        @ApiResponse(code = 401, message = "Sem autenticação"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public ResponseEntity<List<ResponseFile>> getListFiles() {
      List<ResponseFile> files = storageService.getAllDocumentos().map(documento -> {
        String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/documentos/listar/")
            .path(documento.getId_documento().toString())
            .toUriString();
  
        return new ResponseFile(
            documento.getId_documento(),
            documento.getName(),
            fileDownloadUri,
            documento.getType(),
            documento.getDocumento().length);
      }).collect(Collectors.toList());
  
      return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @ApiOperation(value = "Retorna um documento pelo id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Retornou um documento pelo id"),
		@ApiResponse(code = 401, message = "Sem autenticação"),
		@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		@ApiResponse(code = 404, message = "Não encontrado"),
		@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
    @GetMapping("/listar/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {

        Documento documento = storageService.getDocumento(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.parseMediaType(documento.getType()));
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(documento.getDocumento(), headers, HttpStatus.OK);

        return responseEntity;

    }
}

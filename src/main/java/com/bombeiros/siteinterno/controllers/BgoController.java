package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.BgoResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bgo;
import com.bombeiros.siteinterno.repository.BgoRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;
import com.bombeiros.siteinterno.services.BgoSaveDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/bgo")
public class BgoController {
    
    @Autowired
    BgoRepository bgoRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    BgoSaveDocumentoService bgoSaveDocumento;


    @ApiOperation(value = "Retorna uma lista de Bgo's")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornou uma lista de Bgo's"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public ResponseEntity<List<BgoResponseFile>> listarBgo() {
        List<BgoResponseFile> bgos = bgoRepository.findAll().stream().map(bgo -> {

            return new BgoResponseFile(
                bgo.getIdBgo(),
                bgo.getNome(),
                bgo.getNumBgo());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(bgos);
    }

    @ApiOperation(value = "Retorna uma lista de Bgo's e suas respectivas documentos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bgo's e suas documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentResponseFile>> listarBgoDocumentos() {

        List<DocumentResponseFile> files = bgoRepository.findAll().stream().map(bgo -> {

            List<ResponseFile> documentos = bgo.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/documentos/listar/")
                    .path(documento.getIdDocumento().toString())
                    .toUriString();
                
                return new ResponseFile(
                    documento.getIdDocumento(),
                    documento.getName(),
                    fileDownloadUri,
                    documento.getType(),
                    documento.getDocumentoData().length);
              }).collect(Collectors.toList());

              return new DocumentResponseFile(
                bgo.getIdBgo(),
                bgo.getNome(),
                bgo.getNumBgo(),
                documentos);
          }).collect(Collectors.toList());
      
          return ResponseEntity.status(HttpStatus.OK).body(files);  

    }

    @ApiOperation(value = "Cria um Bgo e faz o upload de sua documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um Bgo e fez o upload de sua documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bgo> criarBga(@RequestPart("bgo") Bgo bgo, @RequestPart("file") MultipartFile file)
            throws IOException {

        bgoSaveDocumento.salvar(bgo, file);

        return ResponseEntity.status(HttpStatus.OK).body(bgo);
    }
}


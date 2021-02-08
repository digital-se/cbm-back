package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.BirResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.repository.BirRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;
import com.bombeiros.siteinterno.services.BirSaveDocumentoService;

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
@RequestMapping(value = "/bir")
public class BirController {

    @Autowired
    BirRepository birRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    BirSaveDocumentoService birSaveDocumento;

    @ApiOperation(value = "Retorna uma lista de Bir's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bir's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<BirResponseFile>> listarBir() {
        List<BirResponseFile> birs = birRepository.findAll().stream().map(bir -> {

            return new BirResponseFile(bir.getId_bir(), bir.getNome(), bir.getNum_bir());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(birs);
    }

    @ApiOperation(value = "Retorna uma lista de Bir's e suas respectivas documentos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bir's e seus documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentResponseFile>> listarBirDocumentos() {
        List<DocumentResponseFile> files = birRepository.findAll().stream().map(bir -> {
            List<ResponseFile> documentos = bir.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documentos/listar/")
                        .path(documento.getId_documento().toString()).toUriString();

                return new ResponseFile(documento.getId_documento(), documento.getName(), fileDownloadUri, documento.getType(),
                        documento.getDocumento().length);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bir.getId_bir(), bir.getNome(), bir.getNum_bir(), documentos);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @ApiOperation(value = "Cria um Bir e faz o upload de sua documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um Bir e fez o upload de sua documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bir> criarBir(@RequestPart("bir") Bir bir, @RequestPart("file") MultipartFile file)
            throws IOException {

        birSaveDocumento.salvar(bir, file);

        return ResponseEntity.status(HttpStatus.OK).body(bir);
    }
}

package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.BgaResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bga;
import com.bombeiros.siteinterno.repository.BgaRepository;
import com.bombeiros.siteinterno.repository.ImagemRepository;
import com.bombeiros.siteinterno.services.FileStorageService;

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
@RequestMapping(value = "/bga")
public class BgaController {

    @Autowired
    BgaRepository bgaRepository;

    @Autowired
    ImagemRepository imagemRepository;

    @Autowired
    FileStorageService fileStorage;

    @ApiOperation(value = "Retorna uma lista de Bga's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bga's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<BgaResponseFile>> listarBga() {
        List<BgaResponseFile> bgas = bgaRepository.findAll().stream().map(bga -> {
            
            return new BgaResponseFile(
                bga.getId_bga(),
                bga.getNome(),
                bga.getNum_bga()
            );
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(bgas);
    }

    @ApiOperation(value = "Retorna uma lista de Bga's e suas respectivas imagens")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bga's e suas imagens"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/imagens")
    public ResponseEntity<List<DocumentResponseFile>> listarBgaImagens() {

        List<DocumentResponseFile> files = bgaRepository.findAll().stream().map(bga -> {
            
            List<ResponseFile> imagens = bga.getImagens().stream().map(imagem -> {
                String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/imagens/listar/")
                    .path(imagem.getId_imagem().toString())
                    .toUriString();
          
                return new ResponseFile(
                    imagem.getId_imagem(),
                    imagem.getName(),
                    fileDownloadUri,
                    imagem.getType(),
                    imagem.getImagem().length);
              }).collect(Collectors.toList());

            return new DocumentResponseFile(
              bga.getId_bga(),
              bga.getNome(),
              bga.getNum_bga(),
              imagens);
        }).collect(Collectors.toList());
    
        return ResponseEntity.status(HttpStatus.OK).body(files);

    }
    @ApiOperation(value = "Cria um Bga e faz o upload de sua imagem")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um Bga e fez o upload de sua imagem"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bga> criarBga(@RequestPart("bga") Bga bga, @RequestPart("file") MultipartFile file)
            throws IOException {

        fileStorage.salvar(bga, file);

        return ResponseEntity.status(HttpStatus.OK).body(bga);
    }
}

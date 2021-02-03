package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.BirResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.repository.BirRepository;
import com.bombeiros.siteinterno.repository.ImagemRepository;
import com.bombeiros.siteinterno.services.BirSaveImageService;

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
    ImagemRepository imagemRepository;

    @Autowired
    BirSaveImageService birSaveImage;

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

    @ApiOperation(value = "Retorna uma lista de Bir's e suas respectivas imagens")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bir's e suas imagens"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/imagens")
    public ResponseEntity<List<DocumentResponseFile>> listarBirImagens() {
        List<DocumentResponseFile> files = birRepository.findAll().stream().map(bir -> {
            List<ResponseFile> imagens = bir.getImagens().stream().map(imagem -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/imagens/listar/")
                        .path(imagem.getId_imagem().toString()).toUriString();

                return new ResponseFile(imagem.getId_imagem(), imagem.getName(), fileDownloadUri, imagem.getType(),
                        imagem.getImagem().length);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bir.getId_bir(), bir.getNome(), bir.getNum_bir(), imagens);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @ApiOperation(value = "Cria um Bir e faz o upload de sua imagem")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um Bir e fez o upload de sua imagem"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bir> criarBir(@RequestPart("bir") Bir bir, @RequestPart("file") MultipartFile file)
            throws IOException {

        birSaveImage.salvar(bir, file);

        return ResponseEntity.status(HttpStatus.OK).body(bir);
    }
}

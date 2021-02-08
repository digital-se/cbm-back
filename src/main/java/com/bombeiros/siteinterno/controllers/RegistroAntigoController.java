package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.RegistroAntigoResponseFile;
import com.bombeiros.siteinterno.models.RegistroAntigo;
import com.bombeiros.siteinterno.repository.RegistroAntigoRepository;
import com.bombeiros.siteinterno.services.RegistroAntigoSaveDocumentoService;

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
@RequestMapping(value = "/registroAntigo")
public class RegistroAntigoController {

    @Autowired
    RegistroAntigoRepository registroRepository;

    @Autowired
    RegistroAntigoSaveDocumentoService registroSaveDocumento;

    @ApiOperation(value = "Retorna uma lista de Registros antigos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Registro antigos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<RegistroAntigoResponseFile>> listarRegistroAntigo() {
        List<RegistroAntigoResponseFile> files = registroRepository.findAll().stream().map(registro -> {
            return new RegistroAntigoResponseFile(registro.getId_registroAntigo(), registro.getNome());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/documentos")
    public ResponseEntity<List<RegistroAntigoResponseFile>> listarRegistroDocumentos() {
        List<RegistroAntigoResponseFile> files = registroRepository.findAll().stream().map(registro -> {

            List<ResponseFile> documentos = registro.getDocumentos().stream().map(documento -> {
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

              return new RegistroAntigoResponseFile(
                  registro.getId_registroAntigo(),
                  registro.getNome(), 
                  documentos);
                }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RegistroAntigo> criarRegistroAntigo(@RequestPart("registro") RegistroAntigo registroAntigo,
            @RequestPart("file") MultipartFile file) throws IOException {

        registroSaveDocumento.salvar(registroAntigo, file);

        return ResponseEntity.status(HttpStatus.OK).body(registroAntigo);
    }
}

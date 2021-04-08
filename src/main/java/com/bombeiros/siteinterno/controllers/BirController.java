package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.message.BirResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.services.BirServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/bir")
public class BirController {

    @Autowired
    private BirServices artigoServices;

    // SALVAR
    @ApiOperation(value = "Cria um BIR e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BIR e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<Bir> salvar(@RequestPart("bir") Bir artigo, @RequestPart("file") MultipartFile file)
            throws IOException {

        artigoServices.salvar(artigo, file);

        return ResponseEntity.status(HttpStatus.OK).body(artigo);
    }

    // LISTAR DOCUMENTOS
    @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BIR")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo BIR"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{birid}")
    public ResponseEntity<List<DocumentResponseFile>> listarDocumentos(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getDocumentos(id));
    }

    // LISTAR ARTIGOS
    @ApiOperation(value = "Retorna uma lista de BIR's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de BIR's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/artigos")
    public ResponseEntity<List<BirResponseFile>> listarArtigos() { // adicionar parametro string para query futuramente
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getArtigos());
    }

    /* // done
    @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BIR")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo BIR"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/{birid}")
    public ResponseEntity<List<DocumentResponseFile>> listarDocumentosBir(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(birService.getBirDocumentos(id));
    }

    // done
    @ApiOperation(value = "Cria um BIR e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BIR e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bir> criarBir(@RequestPart("bir") Bir bir, @RequestPart("file") MultipartFile file)
            throws IOException {

        birService.salvar(bir, file);

        return ResponseEntity.status(HttpStatus.OK).body(bir);
    }

    // for tests, remove on prod!!!
    @ApiOperation(value = "Retorna uma lista de BIR's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de BIR's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/birs")
    public ResponseEntity<List<BirResponseFile>> listarBir() {
        return ResponseEntity.status(HttpStatus.OK).body(birService.getBirs());
    }

    // for tests, remove that on prod!!!!
    @ApiOperation(value = "Retorna uma lista de BIR's e seus respectivos documentos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de BIR's e seus documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentResponseFile>> listarBirDocumentos() {
        return ResponseEntity.status(HttpStatus.OK).body(birService.getBirsEDocumentos());

    } */

    // --------------------------

    /* @ApiOperation(value = "Retorna uma lista de Bir's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bir's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/birs")
    public ResponseEntity<List<BirResponseFile>> listarBir() {
        List<BirResponseFile> birs = birRepository.findAll().stream().map(bir -> {

            return new BirResponseFile(bir.getId(), bir.getNome(), bir.getNum());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(birs);
    } */

    /* @ApiOperation(value = "Retorna uma lista de Bir's e suas respectivas documentos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bir's e seus documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentResponseFile>> listarBirDocumentos() {
        List<DocumentResponseFile> files = birRepository.findAll().stream().map(bir -> {
            List<ResponseFile> documentos = bir.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documentos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();

                return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri, documento.getType(),
                        documento.getDocumentoData().length);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bir.getId(), bir.getNome(), bir.getNum(), documentos);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    } */


    /* // Listando todos os documentos de um BIR
    @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BIR")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo BIR"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/{birid}")
    public ResponseEntity<List<DocumentResponseFile>> listarDocumentosBgo(@PathVariable long birid) {

        List<DocumentResponseFile> files = birRepository.findById(birid).stream().map(bir -> {
            List<ResponseFile> documentos = bir.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();

                return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), 0 );
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bir.getId(), bir.getNome(), bir.getNum(), documentos);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);

    } */

    /* @ApiOperation(value = "Cria um Bir e faz o upload de sua documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um Bir e fez o upload de sua documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bir> criarBir(@RequestPart("bir") Bir bir, @RequestPart("file") MultipartFile file)
            throws IOException {

        birSaveDocumento.salvar(bir, file);

        return ResponseEntity.status(HttpStatus.OK).body(bir);
    } */
}

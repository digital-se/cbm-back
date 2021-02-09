package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.BgaResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bga;
import com.bombeiros.siteinterno.repository.BgaRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;
import com.bombeiros.siteinterno.services.BgaSaveDocumentoService;

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
    DocumentoRepository documentoRepository;

    @Autowired
    BgaSaveDocumentoService bgaSaveDocumento;

    @ApiOperation(value = "Retorna uma lista de Bga's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bga's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<BgaResponseFile>> listarBga() {
        List<BgaResponseFile> bgas = bgaRepository.findAll().stream().map(bga -> {

            return new BgaResponseFile(bga.getId_bga(), bga.getNome(), bga.getNum_bga());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(bgas);
    }

    @ApiOperation(value = "Retorna uma lista de Bga's e seus respectivos documentos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Bga's e seus documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentResponseFile>> listarBgaDocumentos() {

        List<DocumentResponseFile> files = bgaRepository.findAll().stream().map(bga -> {

            List<ResponseFile> documentos = bga.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/documentos/listar/").path(documento.getId_documento().toString()).toUriString();

                return new ResponseFile(documento.getId_documento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumento().length);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bga.getId_bga(), bga.getNome(), bga.getNum_bga(), documentos);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);

    }

    //Listando todos os documentos de um BGA -- NR
    @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BGA")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo BGA"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/{bgaid}")
    public ResponseEntity<List<DocumentResponseFile>> listarDocumentosBga(@PathVariable long bgaid) {

        List<DocumentResponseFile> files = bgaRepository.findById(bgaid).stream().map(bga -> {
            List<ResponseFile> documentos = bga.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/documentos/listar/").path(documento.getId_documento().toString()).toUriString();

                return new ResponseFile(documento.getId_documento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumento().length);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bga.getId_bga(), bga.getNome(), bga.getNum_bga(), documentos);
        }).collect(Collectors.toList());

        /* List<DocumentResponseFile> files2 = bgaRepository.findAll().stream().map(bga -> {

            List<ResponseFile> documentos = bga.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/documentos/listar/").path(documento.getId_documento().toString()).toUriString();

                return new ResponseFile(documento.getId_documento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumento().length);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bga.getId_bga(), bga.getNome(), bga.getNum_bga(), documentos);
        }).collect(Collectors.toList());
 */
        return ResponseEntity.status(HttpStatus.OK).body(files);

    }


    @ApiOperation(value = "Cria um Bga e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um Bga e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bga> criarBga(@RequestPart("bga") Bga bga, @RequestPart("file") MultipartFile file)
            throws IOException {

        bgaSaveDocumento.salvar(bga, file);

        return ResponseEntity.status(HttpStatus.OK).body(bga);
    }
}

package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.DTO.ArquivoDTO;
import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.services.DocumentoService;
import com.bombeiros.siteinterno.services.MilitarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private MilitarService militarService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    // SALVAR
    @ApiOperation(value = "Cria um documento vazio")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/criar")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> criar(@RequestBody DocumentoDTO documentoDTO) throws IOException {
        try{
            if(militarService.hasMilitar(documentoDTO.getCriador().getNumMatricula())){
                return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.criar(documentoDTO));
            } else {
                militarService.save(documentoDTO.getCriador().getNumMatricula());
                return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.criar(documentoDTO));
            }
        } catch(NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ADICIONAR ARQUIVO A UM DOCUMENTO
    // @ApiOperation(value = "Cria um BGA e faz o upload de seu documento")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BGA e fez o upload de seu documento"),
    //         @ApiResponse(code = 404, message = "Não encontrado"),
    //         @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @PostMapping("/criar")
    // @ResponseBody
    // public ResponseEntity<DocumentoDTO> criar(@RequestPart("documento") DocumentoDTO documentoDTO) throws IOException {

    //     documentoService.criar(documentoDTO);

    //     return ResponseEntity.status(HttpStatus.CREATED).body(documentoDTO);
    // }

    // LISTAR DOCUMENTOS / precisa implementar elastic search
    @ApiOperation(value = "Retorna uma lista de documentos a partir de um id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/getdocumentos/{id}")
    public ResponseEntity<List<DocumentoDTO>> listarArtigos(@PathVariable long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(documentoService.getDocumentos(id));
    }

    // LISTAR ARQUIVOS / precisa implementar elastic search
    @ApiOperation(value = "Retorna uma lista de arquivos de um respectivo documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou umalista dedocumentos deum respectivo BGA"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/getarquivos/{id}")
    public ResponseEntity<List<ArquivoDTO>> listarDocumentos(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(documentoService.getArquivosDeDocumento(id));
    }

    // // LISTAR DOCUMENTOS
    // @ApiOperation(value = "Retorna uma lista de BGA's")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma
    // lista de BGA's"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @GetMapping("/v1/artigos")
    // public ResponseEntity<List<DocumentoDTO>> listarArtigos() { //adicionar
    // parametro string para query futuramente
    // return
    // ResponseEntity.status(HttpStatus.OK).body(documentService.getArtigos());
    // }

    // // LISTAR TUDO | PARA TESTES, REMOVER FUTURAMENTE
    // @GetMapping("/v1/tudo")
    // public ResponseEntity<List<DocumentoDTO>> listarTudo() {
    // return ResponseEntity.status(HttpStatus.OK).body(documentService.getTudo());

    // }

}

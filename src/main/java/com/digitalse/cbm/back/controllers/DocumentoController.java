package com.digitalse.cbm.back.controllers;

import java.io.IOException;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.mappers.DocumentoMapper;
import com.digitalse.cbm.back.services.DocumentoService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping /* (value = "documento") */
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    /* @Autowired
    private MilitarService militarService; */

    @Autowired
    private DocumentoMapper mapper = Mappers.getMapper(DocumentoMapper.class);

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @ApiOperation(value = "Cria um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Criou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/documentos")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> cadastrar(
            @RequestBody DocumentoDTO documentoDTO/* , @RequestPart List<MultipartFile> files */) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(documentoService.criar(documentoDTO)));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Busca um documento por palavras")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Busca um documento e retornou o mesmo"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> buscar(@PathVariable(name = "palavras") String palavras) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Busca um documento pelo id")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Busca um documento e retornou o mesmo"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> obter(@PathVariable long id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(mapper.toDTO(documentoService.getDocumento(id)));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // todo
    @PutMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> atualizar(@PathVariable long id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // //documento -> arquivo -> ocr
    // @PatchMapping("/documentos/{id}")
    // @ResponseBody
    // public ResponseEntity<DocumentoDTO> dadosOcr(@PathVariable long id) throws
    // IOException {
    // try {
    // return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    // } catch (NullPointerException e) {
    // System.out.println(e);
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // } catch (Exception e) {
    // System.out.println(e);
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
    // }
    // ADICIONAR ARQUIVO A UM DOCUMENTO
    // @ApiOperation(value = "Cria um BGA e faz o upload de seu documento")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BGA e
    // fez o upload de seu documento"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @PostMapping("/criar")
    // @ResponseBody
    // public ResponseEntity<DocumentoDTO> criar(@RequestPart("documento")
    // DocumentoDTO documentoDTO) throws IOException {

    // documentoService.criar(documentoDTO);

    // return ResponseEntity.status(HttpStatus.CREATED).body(documentoDTO);
    // }

    // LISTAR DOCUMENTOS / precisa implementar elastic search
    // @ApiOperation(value = "Retorna uma lista de documentos")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma
    // lista de documentos"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @GetMapping("")
    // public ResponseEntity<List<DocumentoDTO>> listarDocumentos() throws
    // IOException {
    // return
    // ResponseEntity.status(HttpStatus.OK).body(documentoService.getAllDocumentos());
    // }

    // // LISTAR DOCUMENTOS / precisa implementar elastic search
    // @ApiOperation(value = "Retorna uma lista de documentos a partir de um id")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma
    // lista de documentos"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @GetMapping("/getdocumentos/{id}")
    // public ResponseEntity<List<DocumentoDTO>> listarArtigos(@PathVariable long
    // id) throws IOException {
    // return
    // ResponseEntity.status(HttpStatus.OK).body(documentoService.getDocumentos(id));
    // }

    // // LISTAR ARQUIVOS / precisa implementar elastic search
    // @ApiOperation(value = "Retorna uma lista de arquivos de um respectivo
    // documento")
    // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou umalista
    // dedocumentos deum respectivo BGA"),
    // @ApiResponse(code = 404, message = "Não encontrado"),
    // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    // @GetMapping("/getarquivos/{id}")
    // public ResponseEntity<List<ArquivoDTO>> listarArquivos(@PathVariable long id)
    // {
    // return
    // ResponseEntity.status(HttpStatus.OK).body(documentoService.getArquivosDeDocumento(id));
    // }

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

package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.responseFiles.RFBuscaDocumentos;
import com.digitalse.cbm.back.responseFiles.RFDocumento;
import com.digitalse.cbm.back.responseFiles.RFEditarDocumento;
import com.digitalse.cbm.back.services.DocumentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping /* (value = "documento") */
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @ApiOperation(value = "Cria um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Criou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/documentos")
    @ResponseBody
    public ResponseEntity<RFDocumento> cadastrar(
            @RequestBody DocumentoDTO documentoDTO/* , @RequestPart List<MultipartFile> files */) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.criar(documentoDTO));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Busca documentos por campos")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Buscou documentos e retornou os que se encaixam"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    @ResponseBody
    public ResponseEntity<List<RFBuscaDocumentos>> buscar(@RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo, @RequestParam(required = false) String numeracao,
            @RequestParam(required = false) LocalDate dataInicial, @RequestParam(required = false) LocalDate dataFinal,
            @RequestParam(required = false) String matriculaMilitar, @RequestParam(required = false) String nomeMilitar,
            @RequestParam(required = false) String palavras) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("nome", nome);
            node.put("tipo", tipo);
            node.put("numeracao", numeracao);
            node.put("dataInicial", dataInicial.toString());
            node.put("dataFinal", dataFinal.toString());
            node.put("matriculaMilitar", matriculaMilitar);
            node.put("nomeMilitar", nomeMilitar);
            node.put("palavras", palavras);
            System.out.println(mapper.valueToTree(node).toString());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(documentoService.getDocumento(mapper.valueToTree(node)));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Busca um documento pelo id")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Busca um documento e retornou o mesmo"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<RFDocumento> obter(@PathVariable long id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentoService.getDocumento(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "Atualiza um documento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Atualizou um documento e salvou no DB"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PutMapping("/documentos/{id}")
    @ResponseBody
    public ResponseEntity<RFEditarDocumento> atualizar(@PathVariable long id, @RequestBody DocumentoDTO documentoNovo)
            throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentoService.editar(id, documentoNovo));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
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

package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.models.Arquivo;
import com.digitalse.cbm.back.models.Documento;
import com.digitalse.cbm.back.services.DocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class ArquivoController {

    @Autowired
    private DocumentoService documentoService;

    /*
     * @Autowired private ArquivoService arquivoService;
     */

    @PostMapping("/documentos/{documento_id}/arquivos")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> cadastrar(@PathVariable long documento_id, @RequestBody ArquivoDTO arquivoDTO/* , @RequestPart MultipartFile file */)
            throws IOException {
        try {
            Documento doc = documentoService.getDocumento(documento_id);
            doc.getArquivos().add(new Arquivo(/* AQUI */doc, arquivoDTO.getNome(), arquivoDTO.getTipo(), arquivoDTO.getCriador(), arquivoDTO.getDataHoraCadastro(),
            arquivoDTO.getStatus(), arquivoDTO.getNoOcr(), arquivoDTO.getTamanho()));
            documentoService.salvar(/* AQUI */doc);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/documentos/{id}/arquivos")
    @ResponseBody
    public ResponseEntity<List<ArquivoDTO>> listar(@PathVariable long id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentoService.getArquivosDeDocumento(id));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/documentos/{id}/arquivos/{id}")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> obter(@PathVariable long id) throws IOException {
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

    @PutMapping("/documentos/{id}/arquivos/{id}")
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

    @GetMapping("/documentos/{id}/arquivos/{id}/arquivo")
    @ResponseBody
    public ResponseEntity<DocumentoDTO> dadosOcr(@PathVariable long id) throws IOException {
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
}

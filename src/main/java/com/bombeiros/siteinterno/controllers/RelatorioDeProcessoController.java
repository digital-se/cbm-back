package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.RelatorioProcessoDTO;
import com.bombeiros.siteinterno.models.RelatorioDeProcesso;
import com.bombeiros.siteinterno.services.RelatorioDeProcessoServices;

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
@RequestMapping(value = "/rp")
public class RelatorioDeProcessoController {

        @Autowired
        RelatorioDeProcessoServices artigoServices;

        public RelatorioDeProcessoController(RelatorioDeProcessoServices artigoServices) {
                this.artigoServices = artigoServices;
        }

        // SALVAR
        @ApiOperation(value = "Cria um Relatorio de processo e faz o upload de seu documento")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Criou uma Relatorio de processo e fez o upload de seu documento"),
                        @ApiResponse(code = 404, message = "Não encontrado"),
                        @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        @PostMapping("/v1/salvar")
        @ResponseBody
        public ResponseEntity<RelatorioDeProcesso> salvar(@RequestPart("artigo") RelatorioDeProcesso artigo,
                        @RequestPart("file") MultipartFile file) throws IOException {

                artigoServices.salvar(artigo, file);

                return ResponseEntity.status(HttpStatus.CREATED).body(artigo);
        }

        // LISTAR DOCUMENTOS
        @ApiOperation(value = "Retorna uma lista de documentos de um respectivo Relatorio de processo")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo Relatorio de processo"),
                        @ApiResponse(code = 404, message = "Não encontrado"),
                        @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        @GetMapping("/v1/documentos/{rpid}")
        public ResponseEntity<List<DocumentoDTO>> listarDocumentos(@PathVariable long id) {
                return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getDocumentos(id));
        }

        // LISTAR ARTIGOS
        @ApiOperation(value = "Retorna uma lista de Relatorio de processo")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Relatorio de processo"),
                        @ApiResponse(code = 404, message = "Não encontrado"),
                        @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        @GetMapping("/v1/artigos")
        public ResponseEntity<List<RelatorioProcessoDTO>> listarArtigos() { // adicionar parametro string para
                                                                                  // query
                                                                                  // futuramente
                return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getArtigos());
        }

        // ---------------------------------------------------------------------------------------------------------



        // @ApiOperation(value = "Retorna uma lista de Relatorios de processo")
        // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Relatorios de processo"),
        //                 @ApiResponse(code = 404, message = "Não encontrado"),
        //                 @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        // @GetMapping
        // public ResponseEntity<List<RelatorioProcessoResponseFile>> listarRelatorioDeProcesso() {
        //         List<RelatorioProcessoResponseFile> files = relatorioRepository.findAll().stream().map(relatorio -> {
        //                 return new RelatorioProcessoResponseFile(relatorio.getId(), relatorio.getNum());
        //         }).collect(Collectors.toList());

        //         return ResponseEntity.status(HttpStatus.OK).body(files);

        // }

        // @ApiOperation(value = "Retorna uma lista de Relatorios de processo e suas respectivas documentos")
        // @ApiResponses(value = {
        //                 @ApiResponse(code = 200, message = "Retornou uma lista de Relatorios de processo e suas respectivas documentos"),
        //                 @ApiResponse(code = 404, message = "Não encontrado"),
        //                 @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        // @GetMapping("/documentos")
        // public ResponseEntity<List<RelatorioProcessoResponseFile>> listarRelatorioDocumentos() {

        //         List<RelatorioProcessoResponseFile> files = relatorioRepository.findAll().stream().map(relatorio -> {

        //                 List<ResponseFile> documentos = relatorio.getDocumentos().stream().map(documento -> {
        //                         String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //                                         .path("/documentos/listar/").path(documento.getIdDocumento().toString())
        //                                         .toUriString();

        //                         return new ResponseFile(documento.getIdDocumento(), documento.getName(),
        //                                         fileDownloadUri, documento.getType(),
        //                                         documento.getDocumentoData().length);
        //                 }).collect(Collectors.toList());

        //                 return new RelatorioProcessoResponseFile(relatorio.getId(), relatorio.getNum(), documentos);
        //         }).collect(Collectors.toList());

        //         return ResponseEntity.status(HttpStatus.OK).body(files);
        // }

        /*
         * @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BIR")
         * 
         * @ApiResponses(value = { @ApiResponse(code = 200, message =
         * "Retornou uma lista de documentos de um respectivo BIR"),
         * 
         * @ApiResponse(code = 404, message = "Não encontrado"),
         * 
         * @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
         * 
         * @GetMapping("/{rpid}") public
         * ResponseEntity<List<RelatorioProcessoResponseFile>>
         * listarDocumentosBgo(@PathVariable long rpid) {
         * 
         * List<RelatorioProcessoResponseFile> files =
         * relatorioRepository.findById(rpid).stream().map(bir -> { List<ResponseFile>
         * documentos = bir.getDocumentos().stream().map(documento -> { String
         * fileDownloadUri =
         * ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
         * .path(documento.getIdDocumento().toString()).toUriString();
         * 
         * return new ResponseFile(documento.getIdDocumento(), documento.getName(),
         * fileDownloadUri, documento.getType(), 0 documento.getDocumentoData().length);
         * }).collect(Collectors.toList());
         * 
         * return new DocumentResponseFile(bir.getIdBir(), bir.getNome(),
         * bir.getNumBir(), documentos); }).collect(Collectors.toList());
         * 
         * return ResponseEntity.status(HttpStatus.OK).body(files);
         * 
         * }
         */

        // salvar
        // listarDocumentos (id)
        // listarArtigos (query)
        // done
        // @ApiOperation(value = "Cria um Relatorio de Processo e faz o upload de sua documento")
        // @ApiResponses(value = {
        //                 @ApiResponse(code = 200, message = "Criou um Relatorio de Processo e fez o upload de sua documento"),
        //                 @ApiResponse(code = 404, message = "Não encontrado"),
        //                 @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        // @PostMapping
        // @ResponseBody
        // public ResponseEntity<RelatorioDeProcesso> salvar(@RequestPart("relatorio") RelatorioDeProcesso relatorio,
        //                 @RequestPart("file") MultipartFile file) throws IOException {

        //         relatorioSaveDocumento.salvar(relatorio, file);

        //         return ResponseEntity.status(HttpStatus.OK).body(relatorio);
        // }

        // public String listarDocumentos(){
        //         return null;
        // }

        // public String listarArtigos(){
        //         return null;
        // }
}
package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.RegistroAntigoResponseFile;
import com.bombeiros.siteinterno.models.RegistroAntigo;
import com.bombeiros.siteinterno.repository.RegistroAntigoRepository;
import com.bombeiros.siteinterno.services.RegistroAntigoServices;

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
@RequestMapping(value = "/ra")
public class RegistroAntigoController {

        @Autowired
        RegistroAntigoRepository registroRepository;

        @Autowired
        RegistroAntigoServices artigoServices;

        // SALVAR
        @ApiOperation(value = "Cria um Registro Antigo e faz o upload de seu documento")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Criou uma Registro Antigo e fez o upload de seu documento"),
                        @ApiResponse(code = 404, message = "Não encontrado"),
                        @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        @PostMapping("/salvar")
        @ResponseBody
        public ResponseEntity<RegistroAntigo> salvar(@RequestPart("artigo") RegistroAntigo artigo,
                        @RequestPart("file") MultipartFile file) throws IOException {

                artigoServices.salvar(artigo, file);

                return ResponseEntity.status(HttpStatus.OK).body(artigo);
        }

        // LISTAR DOCUMENTOS
        @ApiOperation(value = "Retorna uma lista de documentos de um respectivo Registro Antigo")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo Registro Antigo"),
                        @ApiResponse(code = 404, message = "Não encontrado"),
                        @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        @GetMapping("/documentos/{raid}")
        public ResponseEntity<List<ArtigoResponseFile>> listarDocumentos(@PathVariable long id) {
                return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getDocumentos(id));
        }

        // LISTAR ARTIGOS
        @ApiOperation(value = "Retorna uma lista de Registro Antigo")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Registro Antigo"),
                        @ApiResponse(code = 404, message = "Não encontrado"),
                        @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        @GetMapping("/artigos")
        public ResponseEntity<List<RegistroAntigoResponseFile>> listarArtigos() { // adicionar parametro string para
                                                                                  // query
                                                                                  // futuramente
                return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getArtigos());
        }

        // @ApiOperation(value = "Salva um novo registro antigo no DB")
        // @ApiResponses(value = { @ApiResponse(code = 200, message = "Documento foi
        // salvo com sucesso"),
        // @ApiResponse(code = 404, message = "Não encontrado"),
        // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        // @PostMapping("/post")
        // @ResponseBody
        // public ResponseEntity<RegistroAntigo>
        // salvarRegistroAntigo(@RequestPart("registro") RegistroAntigo registroAntigo,
        // @RequestPart("file") MultipartFile file) throws IOException {

        // artigoServices.salvar(registroAntigo, file);

        // return ResponseEntity.status(HttpStatus.OK).body(registroAntigo);
        // }

        // @ApiOperation(value = "Retorna uma lista de documentos de um registro antigo
        // especifico")
        // @ApiResponses(value = {
        // @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um
        // registro antigo"),
        // @ApiResponse(code = 404, message = "Não encontrado"),
        // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        // @GetMapping("/get/{raid}")
        // public ResponseEntity<List<DocumentResponseFile>>
        // listarDocumentos(@PathVariable long raid) throws IOException {
        // return
        // ResponseEntity.status(HttpStatus.OK).body(artigoServices.listarDocumentos(raid));
        // }

        // @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BGA")
        // @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma
        // lista de documentos de um respectivo BGA"),
        // @ApiResponse(code = 404, message = "Não encontrado"),
        // @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
        // @GetMapping("/{bgaid}")
        // public ResponseEntity<List<DocumentResponseFile>>
        // listarDocumentosBga(@PathVariable long id) {
        // return
        // ResponseEntity.status(HttpStatus.OK).body(bgaSaveDocumentoService.getDocumentos(id));
        // }

        /*
         * @GetMapping("/documentos") public
         * ResponseEntity<List<RegistroAntigoResponseFile>> listarRegistroDocumentos() {
         * List<RegistroAntigoResponseFile> files =
         * registroRepository.findAll().stream().map(registro -> {
         * 
         * List<ResponseFile> documentos =
         * registro.getDocumentos().stream().map(documento -> { String fileDownloadUri =
         * ServletUriComponentsBuilder .fromCurrentContextPath()
         * .path("/documentos/listar/") .path(documento.getIdDocumento().toString())
         * .toUriString();
         * 
         * return new ResponseFile( documento.getIdDocumento(), documento.getName(),
         * fileDownloadUri, documento.getType(), documento.getDocumentoData().length);
         * }).collect(Collectors.toList());
         * 
         * return new RegistroAntigoResponseFile( registro.getId(), registro.getNome(),
         * documentos); }).collect(Collectors.toList());
         * 
         * return ResponseEntity.status(HttpStatus.OK).body(files); }
         */

        /*
         * @ApiOperation(value = "Retorna uma lista de Registros antigos")
         * 
         * @ApiResponses(value = { @ApiResponse(code = 200, message =
         * "Retornou uma lista de Registro antigos"),
         * 
         * @ApiResponse(code = 404, message = "Não encontrado"),
         * 
         * @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
         * 
         * @GetMapping public ResponseEntity<List<RegistroAntigoResponseFile>>
         * listarRegistroAntigo() { List<RegistroAntigoResponseFile> files =
         * registroRepository.findAll().stream().map(registro -> { return new
         * RegistroAntigoResponseFile(registro.getId(), registro.getNome());
         * }).collect(Collectors.toList());
         * 
         * return ResponseEntity.status(HttpStatus.OK).body(files); }
         */
}

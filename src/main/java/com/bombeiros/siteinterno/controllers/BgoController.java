package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.message.BgoResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.models.Bgo;
import com.bombeiros.siteinterno.services.BgoServices;

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
@RequestMapping(value = "/bgo")
public class BgoController {

    @Autowired
    private BgoServices bgoService;

    // done
    @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BGO")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de um respectivo BGO"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/{bgoid}")
    public ResponseEntity<List<DocumentResponseFile>> listarDocumentosBgo(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bgoService.getBgoDocumentos(id));
    }

    // done
    @ApiOperation(value = "Cria um BGO e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou um BGO e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Bgo> criarBgo(@RequestPart("bgo") Bgo bgo, @RequestPart("file") MultipartFile file)
            throws IOException {

        bgoService.salvar(bgo, file);

        return ResponseEntity.status(HttpStatus.OK).body(bgo);
    }

    // for tests, remove on prod!!!
    @ApiOperation(value = "Retorna uma lista de BGO's")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de BGO's"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/bgos")
    public ResponseEntity<List<BgoResponseFile>> listarBgo() {
        return ResponseEntity.status(HttpStatus.OK).body(bgoService.getBgos());
    }

    // for tests, remove that on prod!!!!
    @ApiOperation(value = "Retorna uma lista de BGO's e seus respectivos documentos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de BGO's e seus documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<DocumentResponseFile>> listarBgoDocumentos() {
        return ResponseEntity.status(HttpStatus.OK).body(bgoService.getBgosEDocumentos());

    }

    // --------------------------
    /*
     * @ApiOperation(value = "Retorna uma lista de Bgo's")
     * 
     * @ApiResponses(value = { @ApiResponse(code = 200, message =
     * "Retornou uma lista de Bgo's"),
     * 
     * @ApiResponse(code = 404, message = "Não encontrado"),
     * 
     * @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
     * 
     * @GetMapping("/bgos") public ResponseEntity<List<BgoResponseFile>> listarBgo()
     * { List<BgoResponseFile> bgos = bgoRepository.findAll().stream().map(bgo -> {
     * 
     * return new BgoResponseFile(bgo.getId(), bgo.getNome(), bgo.getNum());
     * }).collect(Collectors.toList());
     * 
     * return ResponseEntity.status(HttpStatus.OK).body(bgos); }
     */

    /*
     * @ApiOperation(value =
     * "Retorna uma lista de Bgo's e suas respectivas documentos")
     * 
     * @ApiResponses(value = { @ApiResponse(code = 200, message =
     * "Retornou uma lista de Bgo's e suas documentos"),
     * 
     * @ApiResponse(code = 404, message = "Não encontrado"),
     * 
     * @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
     * 
     * @GetMapping("/documentos") public ResponseEntity<List<DocumentResponseFile>>
     * listarBgoDocumentos() {
     * 
     * List<DocumentResponseFile> files = bgoRepository.findAll().stream().map(bgo
     * -> {
     * 
     * List<ResponseFile> documentos = bgo.getDocumentos().stream().map(documento ->
     * { String fileDownloadUri =
     * ServletUriComponentsBuilder.fromCurrentContextPath()
     * .path("/documentos/listar/").path(documento.getIdDocumento().toString()).
     * toUriString();
     * 
     * return new ResponseFile(documento.getIdDocumento(), documento.getName(),
     * fileDownloadUri, documento.getType(), 0 ); }).collect(Collectors.toList());
     * 
     * return new DocumentResponseFile(bgo.getId(), bgo.getNome(), bgo.getNum(),
     * documentos); }).collect(Collectors.toList());
     * 
     * return ResponseEntity.status(HttpStatus.OK).body(files);
     * 
     * }
     */
    /*
     * // Listando todos os documentos de um BGO -- NR
     * 
     * @ApiOperation(value = "Retorna uma lista de documentos de um respectivo BGO")
     * 
     * @ApiResponses(value = { @ApiResponse(code = 200, message =
     * "Retornou uma lista de documentos de um respectivo BGO"),
     * 
     * @ApiResponse(code = 404, message = "Não encontrado"),
     * 
     * @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
     * 
     * @GetMapping("/{bgoid}") public ResponseEntity<List<DocumentResponseFile>>
     * listarDocumentosBgo(@PathVariable long bgoid) {
     * 
     * List<DocumentResponseFile> files =
     * bgoRepository.findById(bgoid).stream().map(bgo -> { List<ResponseFile>
     * documentos = bgo.getDocumentos().stream().map(documento -> { String
     * fileDownloadUri =
     * ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
     * .path(documento.getIdDocumento().toString()).toUriString();
     * 
     * return new ResponseFile(documento.getIdDocumento(), documento.getName(),
     * fileDownloadUri, documento.getType(), 0); }).collect(Collectors.toList());
     * 
     * return new DocumentResponseFile(bgo.getId(), bgo.getNome(), bgo.getNum(),
     * documentos); }).collect(Collectors.toList());
     * 
     * return ResponseEntity.status(HttpStatus.OK).body(files);
     * 
     * }
     */

    /*
     * @ApiOperation(value = "Cria um Bgo e faz o upload de sua documento")
     * 
     * @ApiResponses(value = { @ApiResponse(code = 200, message =
     * "Criou um Bgo e fez o upload de sua documento"),
     * 
     * @ApiResponse(code = 404, message = "Não encontrado"),
     * 
     * @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
     * 
     * @PostMapping
     * 
     * @ResponseBody public ResponseEntity<Bgo> criarBga(@RequestPart("bgo") Bgo
     * bgo, @RequestPart("file") MultipartFile file) throws IOException {
     * 
     * bgoService.salvar(bgo, file);
     * 
     * return ResponseEntity.status(HttpStatus.OK).body(bgo); }
     */
}

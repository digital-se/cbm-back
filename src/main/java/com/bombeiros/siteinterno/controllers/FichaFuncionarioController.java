package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.message.FichaFuncionaroResponseFile;
import com.bombeiros.siteinterno.models.FichaFuncionario;
import com.bombeiros.siteinterno.services.FichaFuncionarioServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/fichaFuncionario")
public class FichaFuncionarioController {

    @Autowired
    private FichaFuncionarioServices fdfService;

    @ApiOperation(value = "Salva uma ficha de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Salvou uma ficha de funcionário com sucesso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    public ResponseEntity<FichaFuncionario> criarFichaFuncionario(
            @RequestPart("ficha") FichaFuncionario fichaFuncionario, @RequestPart("file") MultipartFile file)
            throws IOException {

        fdfService.salvarFicha(fichaFuncionario, file);
        return ResponseEntity.status(HttpStatus.OK).body(fichaFuncionario);
    }

    @ApiOperation(value = "Retorna uma lista de Fichas de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Fichas de funcionário"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<FichaFuncionaroResponseFile>> listarFichaFuncionario() {

        List<FichaFuncionaroResponseFile> files = fdfService.listarFichas();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @ApiOperation(value = "Retorna uma lista de documentos a partir do id de uma ficha de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<FichaFuncionaroResponseFile>> listarFichaFuncionarioDocumentos() {

        List<FichaFuncionaroResponseFile> files = fdfService.listarDocumentos();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

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
     * @GetMapping("/{ffid}") public
     * ResponseEntity<List<FichaFuncionaroResponseFile>>
     * listarDocumentosFichaFuncionario(@PathVariable long ffid) {
     * 
     * List<FichaFuncionaroResponseFile> files =
     * fichaRepository.findById(ffid).stream().map(ficha -> { List<ResponseFile>
     * documentos = ficha.getDocumentos().stream().map(documento -> { String
     * fileDownloadUri =
     * ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
     * .path(documento.getIdDocumento().toString()).toUriString();
     * 
     * return new ResponseFile(documento.getIdDocumento(), documento.getName(),
     * fileDownloadUri, documento.getType(), documento.getDocumentoData().length);
     * }).collect(Collectors.toList());
     * 
     * return new DocumentResponseFile(ficha.getId(), ficha.getNome(),
     * ficha.getNum(), documentos); }).collect(Collectors.toList());
     * 
     * return ResponseEntity.status(HttpStatus.OK).body(files);
     * 
     * }
     */

}

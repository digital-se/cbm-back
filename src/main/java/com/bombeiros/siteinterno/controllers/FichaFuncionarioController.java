package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.FichaFuncionarioResponseFile;
import com.bombeiros.siteinterno.models.FichaFuncionario;
import com.bombeiros.siteinterno.services.FichaFuncionarioServices;

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
@RequestMapping(value = "/ff")
public class FichaFuncionarioController {

    @Autowired
    private FichaFuncionarioServices artigoServices;

    public FichaFuncionarioController(FichaFuncionarioServices artigoServices) {
        this.artigoServices = artigoServices;
    }

    // SALVAR
    @ApiOperation(value = "Cria uma Ficha de Funcionário e faz o upload de seu documento")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Criou uma Ficha de Funcionário e fez o upload de seu documento"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<FichaFuncionario> salvar(@RequestPart("artigo") FichaFuncionario artigo, @RequestPart("file") MultipartFile file)
            throws IOException {

        artigoServices.salvar(artigo, file);

        return ResponseEntity.status(HttpStatus.OK).body(artigo);
    }

    // LISTAR DOCUMENTOS
    @ApiOperation(value = "Retorna uma lista de documentos de uma respectiva Ficha de Funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos de uma respectiva Ficha de Funcionário"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos/{ffid}")
    public ResponseEntity<List<ArtigoResponseFile>> listarDocumentos(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getDocumentos(id));
    }

    // LISTAR ARTIGOS
    @ApiOperation(value = "Retorna uma lista de Ficha de Funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Ficha de Funcionário"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/artigos")
    public ResponseEntity<List<FichaFuncionarioResponseFile>> listarArtigos() { // adicionar parametro string para query futuramente
        return ResponseEntity.status(HttpStatus.OK).body(artigoServices.getArtigos());
    }


    /* @ApiOperation(value = "Salva uma ficha de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Salvou uma ficha de funcionário com sucesso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @PostMapping
    public ResponseEntity<FichaFuncionario> criarFichaFuncionario(
            @RequestPart("ficha") FichaFuncionario fichaFuncionario, @RequestPart("file") MultipartFile file)
            throws IOException {

        artigoServices.salvarFicha(fichaFuncionario, file);
        return ResponseEntity.status(HttpStatus.OK).body(fichaFuncionario);
    }

    @ApiOperation(value = "Retorna uma lista de Fichas de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Fichas de funcionário"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<FichaFuncionaroResponseFile>> listarFichaFuncionario() {

        List<FichaFuncionaroResponseFile> files = artigoServices.listarFichas();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @ApiOperation(value = "Retorna uma lista de documentos a partir do id de uma ficha de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de documentos"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping("/documentos")
    public ResponseEntity<List<FichaFuncionaroResponseFile>> listarFichaFuncionarioDocumentos() {

        List<FichaFuncionaroResponseFile> files = artigoServices.listarDocumentos();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    } */

}

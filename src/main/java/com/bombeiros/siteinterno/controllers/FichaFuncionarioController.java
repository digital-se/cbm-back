package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.message.FichaFuncionaroResponseFile;
import com.bombeiros.siteinterno.models.FichaFuncionario;
import com.bombeiros.siteinterno.repository.FichaFuncionarioRepository;
import com.bombeiros.siteinterno.services.FichaFuncionaroSaveImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/fichaFuncionario")
public class FichaFuncionarioController {

    @Autowired
    FichaFuncionarioRepository fichaRepository;

    @Autowired
    FichaFuncionaroSaveImageService saveFichaImage;

    @ApiOperation(value = "Retorna uma lista de Fichas de funcionário")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retornou uma lista de Fichas de funcionário"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção") })
    @GetMapping
    public ResponseEntity<List<FichaFuncionaroResponseFile>> listarFichaFuncionario() {
        List<FichaFuncionaroResponseFile> files = fichaRepository.findAll().stream().map(ficha -> {
            return new FichaFuncionaroResponseFile(ficha.getId_fichaFuncionario(), ficha.getNome(),
                    ficha.getNum_ficha(), ficha.getData_inclusao(), ficha.getData_exclusao());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/imagens")
    public ResponseEntity<List<FichaFuncionaroResponseFile>> listarFichaFuncionarioImagens() {
        List<FichaFuncionaroResponseFile> files = fichaRepository.findAll().stream().map(ficha -> {
            List<ResponseFile> imagens = ficha.getImagens().stream().map(imagem -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/imagens/listar/")
                        .path(imagem.getId_imagem().toString()).toUriString();

                return new ResponseFile(imagem.getId_imagem(), imagem.getName(), fileDownloadUri, imagem.getType(),
                        imagem.getImagem().length);
            }).collect(Collectors.toList());

            return new FichaFuncionaroResponseFile(ficha.getId_fichaFuncionario(), ficha.getNome(),
                    ficha.getNum_ficha(), ficha.getData_inclusao(), ficha.getData_exclusao(), imagens);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping
    public ResponseEntity<FichaFuncionario> criarFichaFuncionario(
            @RequestPart("ficha") FichaFuncionario fichaFuncionario, @RequestPart("file") MultipartFile file)
            throws IOException {
        
        saveFichaImage.salvar(fichaFuncionario, file);

        return ResponseEntity.status(HttpStatus.OK).body(fichaFuncionario);
    }
}

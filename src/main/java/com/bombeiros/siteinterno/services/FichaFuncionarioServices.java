package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.FichaFuncionario;
import com.bombeiros.siteinterno.message.FichaFuncionaroResponseFile;
import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.FichaFuncionarioRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FichaFuncionarioServices {

    @Autowired
    private FichaFuncionarioRepository fichaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    /* 
    listarFichaFuncionario
    listarFichaFuncionarioDocumentos
    */

    @Transactional
    public Documento salvarFicha(FichaFuncionario fichaFuncionario, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());
        fichaRepository.save(fichaFuncionario);
        documento.setFichaFuncionario(fichaFuncionario);
        return documentoRepository.save(documento);
    }

    public List<FichaFuncionaroResponseFile> listarFichas() {
        List<FichaFuncionaroResponseFile> files = fichaRepository.findAll().stream().map(ficha -> {
            return new FichaFuncionaroResponseFile(ficha.getId(), ficha.getNome(), ficha.getNum(),
                    ficha.getDataInclusao(), ficha.getDataExclusao());
        }).collect(Collectors.toList());

        return files;
    }

    public List<FichaFuncionaroResponseFile> listarDocumentos(/* long id */) {
        List<FichaFuncionaroResponseFile> files = fichaRepository.findAll().stream().map(ficha -> {
            List<ResponseFile> documentos = ficha.getDocumentos().stream().map(documento -> {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/documentos/listar/").path(documento.getIdDocumento().toString()).toUriString();

                return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumentoData().length);
            }).collect(Collectors.toList());

            return new FichaFuncionaroResponseFile(ficha.getId(), ficha.getNome(), ficha.getNum(),
                    ficha.getDataInclusao(), ficha.getDataExclusao(), documentos);
        }).collect(Collectors.toList());

        return files;
    }
    
     // Método de retornar uma documento pelo seu ID
     public Documento getDocumentos(Long id) {
        return documentoRepository.findById(id).get();
    }

    // Método de retornar todas as documentos
    public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    }

}

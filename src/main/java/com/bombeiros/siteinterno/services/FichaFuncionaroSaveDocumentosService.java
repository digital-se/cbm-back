package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.FichaFuncionario;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.FichaFuncionarioRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FichaFuncionaroSaveDocumentosService {

    @Autowired
    FichaFuncionarioRepository fichaRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Transactional
    public Documento salvar(FichaFuncionario fichaFuncionario, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        fichaRepository.save(fichaFuncionario);

        documento.setFichaFuncionario(fichaFuncionario);

        return documentoRepository.save(documento);
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

package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.models.RegistroAntigo;
import com.bombeiros.siteinterno.repository.DocumentoRepository;
import com.bombeiros.siteinterno.repository.RegistroAntigoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroAntigoServices {

    @Autowired
    RegistroAntigoRepository registroAntigoRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Transactional
    public Documento salvar(RegistroAntigo registroAntigo, MultipartFile file) throws IOException {
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        registroAntigoRepository.save(registroAntigo);

        documento.setRegistroAntigo(registroAntigo);

        return documentoRepository.save(documento);
    }

    
     // Método de retornar uma documento pelo seu ID
     public Documento getDocumento(Long id) {
        return documentoRepository.findById(id).get();
    }

    // Método de retornar todas as documentos
    public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    }
}

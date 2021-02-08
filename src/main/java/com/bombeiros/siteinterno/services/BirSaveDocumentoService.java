package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.BirRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BirSaveDocumentoService {
    
    @Autowired
    BirRepository birRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Transactional
    public Documento salvar(Bir bir, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento Documento = new Documento(fileName, file.getContentType(), file.getBytes());

        birRepository.save(bir);

        Documento.setBir(bir);

        return documentoRepository.save(Documento);
}

    public Documento getDocumento(Long id) {
        return documentoRepository.findById(id).get();
    }

    public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    }
}

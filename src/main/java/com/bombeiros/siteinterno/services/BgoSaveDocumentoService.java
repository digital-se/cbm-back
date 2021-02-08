package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bgo;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.BgoRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BgoSaveDocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private BgoRepository bgoRepository;

    @Transactional
    public Documento salvar(Bgo bgo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento Documento = new Documento(fileName, file.getContentType(), file.getBytes());

        bgoRepository.save(bgo);

        Documento.setBgo(bgo);

        return documentoRepository.save(Documento);
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

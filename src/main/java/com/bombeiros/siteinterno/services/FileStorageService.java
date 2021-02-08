package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bga;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.BgaRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private BgaRepository bgaRepository;


    // Método de salvar as informações do Bga e uma documento referente ao Bga
    // Dependendo do banco de dados utilizado, provavelmente deverá ser feito alterações no "application.properties" para ser feito o upload de documentos
    // No application.properties você definirá o tamanho máximo que uma documento pode ter para ser salva no banco de dados
    @Transactional
    public Documento salvar(Bga bga, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        bgaRepository.save(bga);
        documento.setBga(bga);

        return documentoRepository.save(documento);
    }

    public Documento salvarDocumento(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

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

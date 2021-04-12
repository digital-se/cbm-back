package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.message.RegistroAntigoResponseFile;
import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.models.RegistroAntigo;
import com.bombeiros.siteinterno.repository.DocumentoRepository;
import com.bombeiros.siteinterno.repository.RegistroAntigoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class RegistroAntigoServices {

    @Autowired
    RegistroAntigoRepository artigoRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    // SALVAR    
    @Transactional
    public Documento salvar(RegistroAntigo artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setRegistroAntigo(artigo);
        documentoRepository.save(documento);

        return documento;
    }

    // LISTAR DOCUMENTOS
    public List<DocumentResponseFile> getDocumentos(Long id) {

        List<DocumentResponseFile> files = artigoRepository.findById(id).stream().map(artigo -> {

            List<ResponseFile> documentos = artigo.getDocumentos().stream().map(documento -> {

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();
                return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumentoData().length);

            }).collect(Collectors.toList());

            return new DocumentResponseFile(artigo.getId(), artigo.getNome(), 0, documentos);
        }).collect(Collectors.toList());

        return files;
    }

    // LISTAR ARTIGOS
    public List<RegistroAntigoResponseFile> getArtigos() {
        List<RegistroAntigoResponseFile> files = artigoRepository.findAll().stream().map(artigo -> {
            return new RegistroAntigoResponseFile(artigo.getId(), artigo.getNome());
        }).collect(Collectors.toList());

        return files;
    }

   /*  @Transactional
    public Documento salvar(RegistroAntigo registroAntigo, MultipartFile file) throws IOException {
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        registroAntigoRepository.save(registroAntigo);

        documento.setRegistroAntigo(registroAntigo);

        return documentoRepository.save(documento);
    } */

    /* public List<DocumentResponseFile> listarDocumentos(long raid) throws IOException {
        List<DocumentResponseFile> files = artigoRepository.findById(raid).stream().map(ra -> {

            List<ResponseFile> documentos = ra.getDocumentos().stream().map(documento -> {

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();
                return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumentoData().length);

            }).collect(Collectors.toList());
            
            //fix it
            return new DocumentResponseFile(ra.getId(), ra.getNome(), 0, documentos);
        }).collect(Collectors.toList());

        return files;
    }
    
     // Método de retornar uma documento pelo seu ID
     public Documento getDocumento(Long id) {
        return documentoRepository.findById(id).get();
    }

    // Método de retornar todas as documentos
    public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    } */
}

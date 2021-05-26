package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.DTO.ArquivoDTO;
import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.RegistroAntigoDTO;
import com.bombeiros.siteinterno.models.Arquivo;
import com.bombeiros.siteinterno.models.RegistroAntigo;
import com.bombeiros.siteinterno.repository.ArtigoRepository;
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
    ArtigoRepository documentoRepository;

    // SALVAR    
    @Transactional
    public Arquivo salvar(RegistroAntigo artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Arquivo documento = new Arquivo(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setRegistroAntigo(artigo);
        documentoRepository.save(documento);

        return documento;
    }

    // LISTAR DOCUMENTOS DE UM ARTIGO
    public List<ArquivoDTO> getDocumentosDeUmArtigo(Long id) {

        List<ArquivoDTO> responseFiles = artigoRepository.getOne(id).getDocumentos().stream().map(documento -> {

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                    .path(documento.getIdDocumento().toString()).toUriString();
            return new ArquivoDTO(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                    documento.getType(), documento.getDocumentoData().length);

        }).collect(Collectors.toList());

        return responseFiles;
    }

    // LISTAR DOCUMENTOS
    public List<DocumentoDTO> getDocumentos(Long id) {

        List<DocumentoDTO> files = artigoRepository.findById(id).stream().map(artigo -> {

            List<ArquivoDTO> documentos = artigo.getDocumentos().stream().map(documento -> {

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();
                return new ArquivoDTO(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumentoData().length);

            }).collect(Collectors.toList());

            return new DocumentoDTO(artigo.getId(), artigo.getNome(), 0, documentos);
        }).collect(Collectors.toList());

        return files;
    }

    // LISTAR ARTIGOS
    public List<RegistroAntigoDTO> getArtigos() {
        List<RegistroAntigoDTO> files = artigoRepository.findAll().stream().map(artigo -> {
            return new RegistroAntigoDTO(artigo.getId(), artigo.getNome());
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

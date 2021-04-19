package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.message.ArtigoResponseFile;
import com.bombeiros.siteinterno.message.BgoResponseFile;
import com.bombeiros.siteinterno.message.DocumentoResponseFile;
import com.bombeiros.siteinterno.models.Bgo;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.BgoRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BgoServices {

    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private BgoRepository artigoRepository;

    // SALVAR    
    @Transactional
    public Documento salvar(Bgo artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setBgo(artigo);
        documentoRepository.save(documento);

        return documento;
    }
    
    // LISTAR DOCUMENTOS DE UM ARTIGO
    public List<DocumentoResponseFile> getDocumentosDeUmArtigo(Long id) {

        List<DocumentoResponseFile> responseFiles = artigoRepository.getOne(id).getDocumentos().stream().map(documento -> {

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                    .path(documento.getIdDocumento().toString()).toUriString();
            return new DocumentoResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                    documento.getType(), documento.getDocumentoData().length);

        }).collect(Collectors.toList());

        return responseFiles;
    }
    
    // LISTAR DOCUMENTOS
    public List<ArtigoResponseFile> getDocumentos(Long id) {

        List<ArtigoResponseFile> files = artigoRepository.findById(id).stream().map(artigo -> {

            List<DocumentoResponseFile> documentos = artigo.getDocumentos().stream().map(documento -> {

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();
                return new DocumentoResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumentoData().length);

            }).collect(Collectors.toList());

            return new ArtigoResponseFile(artigo.getId(), artigo.getNome(), artigo.getNum(), documentos);
        }).collect(Collectors.toList());

        return files;
    }

    // LISTAR ARTIGOS
    public List<BgoResponseFile> getArtigos() {
        List<BgoResponseFile> files = artigoRepository.findAll().stream().map(artigo -> {
            return new BgoResponseFile(artigo.getId(), artigo.getNome(), artigo.getNum());
        }).collect(Collectors.toList());

        return files;
    }



    //------ LIXO TEMPORARIO ------

    // @Transactional
    // public Documento salvar(Bgo bgo, MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

    //     bgoRepository.save(bgo);
    //     documento.setBgo(bgo);
    //     documentoRepository.save(documento);

    //     return documento;
    // }

    // public Documento salvarDocumento(MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

    //     return documentoRepository.save(documento);
    // }

    // for tests, remove that on prod!!!!
    // public List<BgoResponseFile> getBgos() {
    //     List<BgoResponseFile> bgos = artigoRepository.findAll().stream().map(bgo -> {
    //         return new BgoResponseFile(bgo.getId(), bgo.getNome(), bgo.getNum());
    //     }).collect(Collectors.toList());

    //     return bgos;
    // }

    // Método de retornar uma documento pelo seu ID // Need to implement exceptions
    // public Documento getDocumentos(Long id) {
    //     return documentoRepository.findById(id).get();
    // }

    // Método de retornar todas as documentos
    // public Stream<Documento> getAllDocumentos() {
    //     return documentoRepository.findAll().stream();
    // }

    // need refatoration / for tests, remove that on prod!!!!
    // public List<DocumentResponseFile> getBgosEDocumentos() {
    //     // to implement:
    //     // get last b--, send 10 lasts and return the id of the last one as recursive
    //     // method
    //     // if next page, call the method again passing that x-10-1 as the next id for
    //     // the next 10 docs

    //     List<DocumentResponseFile> files = artigoRepository.findAll().stream().map(bgo -> {

    //         List<Documento> documentos = bgo.getDocumentos();
    //         Stream<Documento> documentosStream = documentos.stream();
    //         List<ResponseFile> documentosRF = null;

    //         documentosRF = documentosStream.map(documento -> {

    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    //                     .path(documento.getIdDocumento().toString()).toUriString();

    //             // broken length
    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), 0/* documento.getDocumentoData().length */);
    //         }).collect(Collectors.toList());

    //         return new DocumentResponseFile(bgo.getId(), bgo.getNome(), bgo.getNum(), documentosRF);
    //     }).collect(Collectors.toList());

    //     return files;
    // }

    // done
    // public List<DocumentResponseFile> getBgoDocumentos(Long id) {

    //     List<DocumentResponseFile> files = artigoRepository.findById(id).stream().map(bgo -> {

    //         List<ResponseFile> documentos = bgo.getDocumentos().stream().map(documento -> {

    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    //                     .path(documento.getIdDocumento().toString()).toUriString();
    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), documento.getDocumentoData().length);

    //         }).collect(Collectors.toList());

    //         return new DocumentResponseFile(bgo.getId(), bgo.getNome(), bgo.getNum(), documentos);
    //     }).collect(Collectors.toList());

    //     return files;
    // }



    //------------------------------------------ OLD
    /* @Transactional
    public Documento salvar(Bgo bgo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        bgoRepository.save(bgo);
        documento.setBgo(bgo);
        documentoRepository.save(documento);

        return documento;
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

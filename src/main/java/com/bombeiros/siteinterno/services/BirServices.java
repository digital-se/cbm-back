package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.Bir;
import com.bombeiros.siteinterno.DTO.ArquivoDTO;
import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.BirDTO;
import com.bombeiros.siteinterno.models.Arquivo;
import com.bombeiros.siteinterno.repository.BirRepository;
import com.bombeiros.siteinterno.repository.ArtigoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BirServices {

    @Autowired
    ArtigoRepository documentoRepository;

    @Autowired
    BirRepository artigoRepository;

    // SALVAR    
    @Transactional
    public Arquivo salvar(Bir artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Arquivo documento = new Arquivo(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setBir(artigo);
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

            return new DocumentoDTO(artigo.getId(), artigo.getNome(), artigo.getNum(), documentos);
        }).collect(Collectors.toList());

        return files;
    }

    // LISTAR ARTIGOS
    public List<BirDTO> getArtigos() {
        List<BirDTO> files = artigoRepository.findAll().stream().map(artigo -> {
            return new BirDTO(artigo.getId(), artigo.getNome(), artigo.getNum());
        }).collect(Collectors.toList());

        return files;
    }


    // @Transactional
    // public Documento salvar(Bir bir, MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

    //     birRepository.save(bir);
    //     documento.setBir(bir);
    //     documentoRepository.save(documento);

    //     return documento;
    // }

    // public Documento salvarDocumento(MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

    //     return documentoRepository.save(documento);
    // }

    // // for tests, remove that on prod!!!!
    // public List<BirResponseFile> getBirs() {
    //     List<BirResponseFile> birs = birRepository.findAll().stream().map(bir -> {
    //         return new BirResponseFile(bir.getId(), bir.getNome(), bir.getNum());
    //     }).collect(Collectors.toList());

    //     return birs;
    // }

    // // Método de retornar uma documento pelo seu ID // Need to implement exceptions
    // public Documento getDocumento(Long id) {
    //     return documentoRepository.findById(id).get();
    // }

    // // Método de retornar todas as documentos
    // public Stream<Documento> getAllDocumentos() {
    //     return documentoRepository.findAll().stream();
    // }

    // // need refatoration / for tests, remove that on prod!!!!
    // public List<DocumentResponseFile> getBirsEDocumentos() {
    //     // to implement:
    //     // get last b--, send 10 lasts and return the id of the last one as recursive
    //     // method
    //     // if next page, call the method again passing that x-10-1 as the next id for
    //     // the next 10 docs

    //     List<DocumentResponseFile> files = birRepository.findAll().stream().map(bir -> {

    //         List<Documento> documentos = bir.getDocumentos();
    //         Stream<Documento> documentosStream = documentos.stream();
    //         List<ResponseFile> documentosRF = null;

    //         documentosRF = documentosStream.map(documento -> {

    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    //                     .path(documento.getIdDocumento().toString()).toUriString();

    //             // broken length
    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), 0/* documento.getDocumentoData().length */);
    //         }).collect(Collectors.toList());

    //         return new DocumentResponseFile(bir.getId(), bir.getNome(), bir.getNum(), documentosRF);
    //     }).collect(Collectors.toList());

    //     return files;
    // }

    // // done
    // public List<DocumentResponseFile> getBirDocumentos(Long id) {

    //     List<DocumentResponseFile> files = birRepository.findById(id).stream().map(bir -> {

    //         List<ResponseFile> documentos = bir.getDocumentos().stream().map(documento -> {

    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    //                     .path(documento.getIdDocumento().toString()).toUriString();
    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), documento.getDocumentoData().length);

    //         }).collect(Collectors.toList());

    //         return new DocumentResponseFile(bir.getId(), bir.getNome(), bir.getNum(), documentos);
    //     }).collect(Collectors.toList());

    //     return files;
    // }


    //--------------------------- OLD
    /* @Transactional
    public Documento salvar(Bir bir, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        birRepository.save(bir);
        documento.setBir(bir);
        documentoRepository.save(documento);

        return documento;
    }

    public Documento getDocumento(Long id) {
        return documentoRepository.findById(id).get();
    }

    public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    } */
}

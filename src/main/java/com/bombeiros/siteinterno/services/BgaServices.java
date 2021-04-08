package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.message.BgaResponseFile;
import com.bombeiros.siteinterno.message.DocumentResponseFile;
import com.bombeiros.siteinterno.message.ResponseFile;
import com.bombeiros.siteinterno.models.Bga;
import com.bombeiros.siteinterno.models.Documento;
import com.bombeiros.siteinterno.repository.BgaRepository;
import com.bombeiros.siteinterno.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BgaServices {

    // use constructor injection on documentRepository!!!!
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private BgaRepository artigoRepository;

    // Método de salvar as informações do Bga e uma documento referente ao Bga
    // Dependendo do banco de dados utilizado, provavelmente deverá ser feito
    // alterações no "application.properties" para ser feito o upload de documentos
    // No application.properties você definirá o tamanho máximo que uma documento
    // pode ter para ser salva no banco de dados

    // SALVAR    
    @Transactional
    public Documento salvar(Bga artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setBga(artigo);
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

            return new DocumentResponseFile(artigo.getId(), artigo.getNome(), artigo.getNum(), documentos);
        }).collect(Collectors.toList());

        return files;
    }

    // LISTAR ARTIGOS
    public List<BgaResponseFile> getArtigos() {
        List<BgaResponseFile> files = artigoRepository.findAll().stream().map(artigo -> {
            return new BgaResponseFile(artigo.getId(), artigo.getNome(), artigo.getNum());
        }).collect(Collectors.toList());

        return files;
    }

    


    //------ EXTRAS TEMPORARIOS ------

    // need refatoration / for tests, remove that on prod!!!!
    public List<DocumentResponseFile> getBgasEDocumentos() {
        // to implement:
        // get last bga, send 10 lasts and return the id of the last one as recursive
        // method
        // if next page, call the method again passing that x-10-1 as the next id for
        // the next 10 docs

        List<DocumentResponseFile> files = artigoRepository.findAll().stream().map(bga -> {

            List<Documento> documentos = bga.getDocumentos();
            Stream<Documento> documentosStream = documentos.stream();
            List<ResponseFile> documentosRF = null;

            documentosRF = documentosStream.map(documento -> {

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();

                // todo
                return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), 0/* documento.getDocumentoData().length */);
            }).collect(Collectors.toList());

            return new DocumentResponseFile(bga.getId(), bga.getNome(), bga.getNum(), documentosRF);
        }).collect(Collectors.toList());

        return files;
    }

    //Necessario no documentoUploadController por algum motivo
    // public Documento salvarDocumento(MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

    //     return documentoRepository.save(documento);
    // }

    // Método de retornar uma documento pelo seu ID, porem ele itera por todos os documentos... não apenas no bga.
    /* public Documento getDocumento(Long id) {
        return documentoRepository.findById(id).get();
    } */

    // Método de retornar todas as documentos
    /* public Stream<Documento> getAllDocumentos() {
        return documentoRepository.findAll().stream();
    } */

}

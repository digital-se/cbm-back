package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.DTO.ArquivoDTO;
import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.RelatorioProcessoDTO;
import com.bombeiros.siteinterno.models.Arquivo;
import com.bombeiros.siteinterno.models.RelatorioDeProcesso;
import com.bombeiros.siteinterno.repository.ArtigoRepository;
import com.bombeiros.siteinterno.repository.RelatorioDeProcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class RelatorioDeProcessoServices {

    @Autowired
    RelatorioDeProcessoRepository artigoRepository;

    @Autowired
    ArtigoRepository documentoRepository;

    // SALVAR    
    @Transactional
    public Arquivo salvar(RelatorioDeProcesso artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Arquivo documento = new Arquivo(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setRelatorioDeProcesso(artigo);
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

    // LISTAR DOCUMENTOS TODO
    public List<DocumentoDTO> getDocumentos(Long id) {

        List<DocumentoDTO> files = artigoRepository.findById(id).stream().map(artigo -> {

            List<ArquivoDTO> documentos = artigo.getDocumentos().stream().map(documento -> {

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
                        .path(documento.getIdDocumento().toString()).toUriString();
                return new ArquivoDTO(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
                        documento.getType(), documento.getDocumentoData().length);

            }).collect(Collectors.toList());

            return new DocumentoDTO(artigo.getId(), "", 0, documentos);
        }).collect(Collectors.toList());

        return files;
    }

    // LISTAR ARTIGOS TODO
    public List<RelatorioProcessoDTO> getArtigos() {
        List<RelatorioProcessoDTO> files = artigoRepository.findAll().stream().map(artigo -> {
            return new RelatorioProcessoDTO(artigo.getId(), 0);
        }).collect(Collectors.toList());

        return files;
    }


    //-----------------------------------------------------------

    // salvar
    // listarDocumentos (id)
    // listarArtigos (query)

    // @Transactional
    // public Documento salvar(RelatorioDeProcesso relatorioProcesso, MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());

    //     rpRepository.save(relatorioProcesso);

    //     documento.setRelatorioDeProcesso(relatorioProcesso);

    //     return documentoRepository.save(documento);
    // }

    // public List<RelatorioProcessoResponseFile> listarDocumentos() throws IOException {
    //     List<RelatorioProcessoResponseFile> files = rpRepository.findAll().stream().map(relatorio -> {

    //         List<ResponseFile> documentos = relatorio.getDocumentos().stream().map(documento -> {
    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    //                     .path("/documentos/listar/").path(documento.getIdDocumento().toString()).toUriString();

    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), documento.getDocumentoData().length);
    //         }).collect(Collectors.toList());

    //         return new RelatorioProcessoResponseFile(relatorio.getId(), relatorio.getNum(), documentos);
    //     }).collect(Collectors.toList());
    //     return files;
    // }

    // public List<DocumentResponseFile> listarArtigos() throws IOException {
    //     List<DocumentResponseFile> files = rpRepository.findAll().stream().map(rp -> {

    //         List<Documento> documentos = rp.getDocumentos();
    //         Stream<Documento> documentosStream = documentos.stream();
    //         List<ResponseFile> documentosRF = null;

    //         documentosRF = documentosStream.map(documento -> {

    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    //                     .path(documento.getIdDocumento().toString()).toUriString();

    //             // todo
    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), 0/* documento.getDocumentoData().length */);
    //         }).collect(Collectors.toList());

    //         return new DocumentResponseFile(rp.getId(), "", rp.getNum(), documentosRF);
    //     }).collect(Collectors.toList());

    //     return files;
    // }

    // public Documento getDocumento(Long id) {
    //     return documentoRepository.findById(id).get();
    // }

    // public Stream<Documento> getAllDocumentos() {
    //     return documentoRepository.findAll().stream();
    // }
}

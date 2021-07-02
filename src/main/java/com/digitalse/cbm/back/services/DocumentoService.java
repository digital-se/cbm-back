package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    

    public Documento salvar(Documento documento) throws IOException {
        return documentoRepository.save(documento);
    }

    // mudar para string
    public Documento getDocumento(Long id) throws IOException {
        Documento doc = documentoRepository.findById(id).get();
        return doc;
    }

    public Documento criar(DocumentoDTO documento) throws IOException {
        // militarService.getByMatricula(documento.getCriador().getMatricula());
        Documento doc = documentoRepository.save(
                new Documento(documento.getId(), documento.getNome(), documento.getNumeracao(), documento.getPublico(),
                        documento.getTipo(), documento.getData(), documento.getDescricao(), /* criado */new Date(),
                        /* atualizado */new Date(), documento.getArquivos(), documento.getMilitares()));
        return doc;
    }

    public List<Documento> getDocumentos(Long id) throws IOException {
        List<Documento> list = documentoRepository.findAll().stream().map(documento -> {
            return new Documento(documento.getId(), documento.getNome(), documento.getNumeracao(),
                    documento.getPublico(), documento.getTipo(), documento.getData(), documento.getDescricao(),
                    /* criado */new Date(), /* atualizado */new Date(), documento.getArquivos(),
                    documento.getMilitares());
        }).collect(Collectors.toList());
        return list;
    }

    public List<Documento> getAllDocumentos() throws IOException {
        List<Documento> list = documentoRepository.findAll().stream().map(documento -> {
            return new Documento(documento.getId(), documento.getNome(), documento.getNumeracao(),
                    documento.getPublico(), documento.getTipo(), documento.getData(), documento.getDescricao(),
                    documento.getCriado(), documento.getAtualizado(), /* arquivos */null, documento.getMilitares());
        }).collect(Collectors.toList());
        return list;
    }

    public List<Arquivo> getArquivosDeDocumento(Long id) {
        List<Arquivo> arquivoPai = documentoRepository.getOne(id).getArquivos().stream().map(arquivo -> {
            return new Arquivo(arquivo.getId(), /* doc */null, arquivo.getNome(), arquivo.getOcr(),
            arquivo.getStatus(), arquivo.getMime(), arquivo.getTamanho(), /* dados */null, /* texto */null,arquivo.getCriado(),
            arquivo.getAtualizado());
        }).collect(Collectors.toList());
        return arquivoPai;
    }

    // ------ V1 ------

    // // SALVAR
    // @Transactional
    // public Documento salvarV1(Bga artigo, MultipartFile file) throws IOException
    // {
    // String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    // Documento documento = new Documento(fileName, file.getContentType(),
    // file.getBytes());

    // artigoRepository.save(artigo);
    // documento.setBga(artigo);
    // documentoRepository.save(documento);

    // return documento;
    // }

    // // LISTAR DOCUMENTOS
    // public List<ArtigoResponseFile> getDocumentosV1(Long id) {

    // List<ArtigoResponseFile> files =
    // artigoRepository.findById(id).stream().map(artigo -> {

    // List<DocumentoResponseFile> documentos =
    // artigo.getDocumentos().stream().map(documento -> {

    // String fileDownloadUri =
    // ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    // .path(documento.getIdDocumento().toString()).toUriString();
    // return new DocumentoResponseFile(documento.getIdDocumento(),
    // documento.getName(), fileDownloadUri,
    // documento.getType(), documento.getDocumentoData().length);

    // }).collect(Collectors.toList());

    // return new ArtigoResponseFile(artigo.getId(), artigo.getNome(),
    // artigo.getNum(), documentos);
    // }).collect(Collectors.toList());

    // return files;
    // }

    // // LISTAR ARTIGOS
    // public List<BgaResponseFile> getArtigosV1() {
    // List<BgaResponseFile> files = artigoRepository.findAll().stream().map(artigo
    // -> {
    // return new BgaResponseFile(artigo.getId(), artigo.getNome(),
    // artigo.getNum());
    // }).collect(Collectors.toList());

    // return files;
    // }

    // // LISTAR DOCUMENTOS DE UM ARTIGO
    // public List<DocumentoResponseFile> getDocumentosDeUmArtigoV1(Long id) {

    // List<DocumentoResponseFile> responseFiles =
    // artigoRepository.getOne(id).getDocumentos().stream().map(documento -> {

    // String fileDownloadUri =
    // ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    // .path(documento.getIdDocumento().toString()).toUriString();
    // return new DocumentoResponseFile(documento.getIdDocumento(),
    // documento.getName(), fileDownloadUri,
    // documento.getType(), documento.getDocumentoData().length);

    // }).collect(Collectors.toList());

    // return responseFiles;
    // }

    // //------ EXTRAS TEMPORARIOS ------

    // // need refatoration / for tests, remove that on prod!!!!
    // public List<ArtigoResponseFile> getTudo() {
    // // to implement:
    // // get last bga, send 10 lasts and return the id of the last one as recursive
    // // method
    // // if next page, call the method again passing that x-10-1 as the next id for
    // // the next 10 docs

    // List<ArtigoResponseFile> files =
    // artigoRepository.findAll().stream().map(artigo -> {

    // List<Documento> documentos = artigo.getDocumentos();
    // Stream<Documento> documentosStream = documentos.stream();
    // List<DocumentoResponseFile> documentosRF = null;

    // documentosRF = documentosStream.map(documento -> {

    // String fileDownloadUri =
    // ServletUriComponentsBuilder.fromCurrentContextPath().path("/todos/listar/")
    // .path(documento.getIdDocumento().toString()).toUriString();

    // // todo
    // return new DocumentoResponseFile(documento.getIdDocumento(),
    // documento.getName(), fileDownloadUri,
    // documento.getType(), 0/* documento.getDocumentoData().length */);
    // }).collect(Collectors.toList());

    // return new ArtigoResponseFile(artigo.getId(), artigo.getNome(),
    // artigo.getNum(), documentosRF);
    // }).collect(Collectors.toList());

    // return files;
    // }

    // Necessario no documentoUploadController por algum motivo
    // public Documento salvarDocumento(MultipartFile file) throws IOException {
    // String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    // Documento documento = new Documento(fileName, file.getContentType(),
    // file.getBytes());

    // return documentoRepository.save(documento);
    // }

    // Método de retornar uma documento pelo seu ID, porem ele itera por todos os
    // documentos... não apenas no bga.
    /*
     * public Documento getDocumento(Long id) { return
     * documentoRepository.findById(id).get(); }
     */

    // Método de retornar todas as documentos
    /*
     * public Stream<Documento> getAllDocumentos() { return
     * documentoRepository.findAll().stream(); }
     */

}

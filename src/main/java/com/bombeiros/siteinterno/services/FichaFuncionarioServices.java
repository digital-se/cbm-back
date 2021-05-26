package com.bombeiros.siteinterno.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.bombeiros.siteinterno.models.FichaFuncionario;
import com.bombeiros.siteinterno.DTO.ArquivoDTO;
import com.bombeiros.siteinterno.DTO.DocumentoDTO;
import com.bombeiros.siteinterno.DTO.FichaFuncionarioDTO;
import com.bombeiros.siteinterno.models.Arquivo;
import com.bombeiros.siteinterno.repository.FichaFuncionarioRepository;
import com.bombeiros.siteinterno.repository.ArtigoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FichaFuncionarioServices {

    

    @Autowired
    private ArtigoRepository documentoRepository;

    @Autowired
    private FichaFuncionarioRepository artigoRepository;

    // SALVAR    
    @Transactional
    public Arquivo salvar(FichaFuncionario artigo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Arquivo documento = new Arquivo(fileName, file.getContentType(), file.getBytes());

        artigoRepository.save(artigo);
        documento.setFichaFuncionario(artigo);
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
    public List<FichaFuncionarioDTO> getArtigos() {
        List<FichaFuncionarioDTO> files = artigoRepository.findAll().stream().map(artigo -> {
            return new FichaFuncionarioDTO(artigo.getId(), artigo.getNome(), artigo.getNum(), artigo.getDataInclusao(), artigo.getDataExclusao());
        }).collect(Collectors.toList());

        return files;
    }

    // @Transactional
    // public Documento salvarFicha(FichaFuncionario fichaFuncionario, MultipartFile file) throws IOException {
    //     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //     Documento documento = new Documento(fileName, file.getContentType(), file.getBytes());
    //     artigoRepository.save(fichaFuncionario);
    //     documento.setFichaFuncionario(fichaFuncionario);
    //     return documentoRepository.save(documento);
    // }

    // public List<FichaFuncionaroResponseFile> listarFichas() {
    //     List<FichaFuncionaroResponseFile> files = artigoRepository.findAll().stream().map(ficha -> {
    //         return new FichaFuncionaroResponseFile(ficha.getId(), ficha.getNome(), ficha.getNum(),
    //                 ficha.getDataInclusao(), ficha.getDataExclusao());
    //     }).collecFichaFuncionaroResponseFilet(Collectors.toList());

    //     return files;
    // }

    // public List<FichaFuncionaroResponseFile> listarDocumentos(/* long id */) {
    //     List<FichaFuncionaroResponseFile> files = artigoRepository.findAll().stream().map(ficha -> {
    //         List<ResponseFile> documentos = ficha.getDocumentos().stream().map(documento -> {
    //             String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    //                     .path("/documentos/listar/").path(documento.getIdDocumento().toString()).toUriString();

    //             return new ResponseFile(documento.getIdDocumento(), documento.getName(), fileDownloadUri,
    //                     documento.getType(), documento.getDocumentoData().length);
    //         }).collect(Collectors.toList());

    //         return new FichaFuncionaroResponseFile(ficha.getId(), ficha.getNome(), ficha.getNum(),
    //                 ficha.getDataInclusao(), ficha.getDataExclusao(), documentos);
    //     }).collect(Collectors.toList());

    //     return files;
    // }
    
    //  // Método de retornar uma documento pelo seu ID
    //  public Documento getDocumentos(Long id) {
    //     return documentoRepository.findById(id).get();
    // }

    // // Método de retornar todas as documentos
    // public Stream<Documento> getAllDocumentos() {
    //     return documentoRepository.findAll().stream();
    // }

}

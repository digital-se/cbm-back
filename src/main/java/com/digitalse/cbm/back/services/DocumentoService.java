package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.mappers.DocumentoMapper;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.repository.specifications.DocumentoSpecification;
import com.digitalse.cbm.back.responseFiles.RFBuscaDocumentos;
import com.digitalse.cbm.back.responseFiles.RFDocumento;
import com.digitalse.cbm.back.responseFiles.RFEditarDocumento;
import com.fasterxml.jackson.databind.JsonNode;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private DocumentoMapper mapperDoc = Mappers.getMapper(DocumentoMapper.class);

    /**
     * Cria um novo documento
     * @param documento DTO do documento a ser salvo
     * @return retorna um response file do documento salvo
     * @throws IOException
     */
    public RFDocumento criar(DocumentoDTO documento) throws IOException {
        // militarService.getByMatricula(documento.getCriador().getMatricula());
        Documento docModel = mapperDoc.toModel(documento);
        RFDocumento rfdoc = new RFDocumento(documentoRepository.save(docModel));
        return rfdoc;
    }

    /**
     * Edita um documento a par
     * @param id
     * @param documentodto
     * @return
     * @throws IOException
     */
    public RFEditarDocumento editar(long id, DocumentoDTO documentodto) throws IOException {
        Documento doc = documentoRepository.findById(id).get();

        if(!documentodto.getTipo().equals(null) && !documentodto.getTipo().isEmpty() && !documentodto.getTipo().isBlank()) doc.setTipo(documentodto.getTipo());
        if(!documentodto.getNumeracao().equals(null) && !documentodto.getNumeracao().isEmpty() && !documentodto.getNumeracao().isBlank()) doc.setNumeracao(documentodto.getNumeracao());
        if(!documentodto.getNome().equals(null) && !documentodto.getNome().isEmpty() && !documentodto.getNome().isBlank()) doc.setNome(documentodto.getNome());
        if(!documentodto.getDescricao().equals(null) && !documentodto.getDescricao().isEmpty() && !documentodto.getDescricao().isBlank()) doc.setDescricao(documentodto.getDescricao());
        if(!documentodto.getData().equals(null)) doc.setData(documentodto.getData());
        if(!documentodto.getMilitares().equals(null) && !documentodto.getMilitares().isEmpty()) doc.setMilitares(documentodto.getMilitares());
        if(!documentodto.getPublico().equals(null))doc.setPublico(documentodto.getPublico());
        documentoRepository.save(doc);
        return new RFEditarDocumento(doc);
    }

    /**
     * Busca um documento pelo id
     * @param id id do documento a ser procurado
     * @return retorna um response file do documento
     * @throws IOException
     */
    public RFDocumento getDocumento(Long id) throws IOException {
        Documento doc = documentoRepository.findById(id).get();
        RFDocumento rfdoc = new RFDocumento(doc);
        return rfdoc;
    }

    /**
     * Busca um documento utilizando multiplos campos e uma specification
     * @param docJson jsonNode gerada com os campos de busca
     * @return uma lista de responsefiles de documentos
     * @throws IOException
     */
    public List<RFBuscaDocumentos> getDocumento(JsonNode docJson) throws IOException {
        DocumentoSpecification ds = new DocumentoSpecification(docJson);
        
        List<RFBuscaDocumentos> rfDocList = documentoRepository.findAll(ds).stream().map(documento -> {
            RFBuscaDocumentos newRFDoc = new RFBuscaDocumentos(documento);
            return newRFDoc;
        }).collect(Collectors.toList());
        /* List<RFBuscaDocumentos> rfDocList = documentoRepository.findAll().stream().map(documento -> {
            RFBuscaDocumentos newRFDoc = new RFBuscaDocumentos(documento);
            return newRFDoc;
        }).collect(Collectors.toList()); */
        return rfDocList;
    }
}

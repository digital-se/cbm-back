package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.Date;
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

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private DocumentoMapper mapperDoc = Mappers.getMapper(DocumentoMapper.class);

    public RFDocumento criar(DocumentoDTO documento) throws IOException {
        // militarService.getByMatricula(documento.getCriador().getMatricula());
        Documento docModel = mapperDoc.toModel(documento);
        RFDocumento rfdoc = new RFDocumento(documentoRepository.save(docModel));
        return rfdoc;
    }

    //TODO: correção para não adicionar valores nulos
    public RFEditarDocumento editar(long id, DocumentoDTO documentodto) throws IOException {
        Documento doc = documentoRepository.findById(id).get();

        doc.setTipo(documentodto.getTipo());
        doc.setNumeracao(documentodto.getNumeracao());
        doc.setNome(documentodto.getNome());
        doc.setDescricao(documentodto.getDescricao());
        doc.setData(documentodto.getData());
        doc.setAtualizado(new Date());
        doc.setMilitares(documentodto.getMilitares());
        doc.setPublico(documentodto.getPublico());
        documentoRepository.save(doc);
        return new RFEditarDocumento(doc);
    }

    public RFDocumento getDocumento(Long id) throws IOException {
        Documento doc = documentoRepository.findById(id).get();
        RFDocumento rfdoc = new RFDocumento(doc);
        return rfdoc;
    }

    public List<RFBuscaDocumentos> getDocumento(DocumentoDTO docDto) throws IOException {
        DocumentoSpecification ds = new DocumentoSpecification(mapperDoc.toModel(docDto));
        
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

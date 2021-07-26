package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.mappers.DocumentoMapper;
import com.digitalse.cbm.back.repository.DocumentoRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private DocumentoMapper mapperDoc = Mappers.getMapper(DocumentoMapper.class);

    public DocumentoDTO salvar(DocumentoDTO documentodto) throws IOException {
        return mapperDoc.toDTO(documentoRepository.save(mapperDoc.toModel(documentodto)));
    }

    public DocumentoDTO editar(long id, DocumentoDTO documentodto) throws IOException {
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
        return mapperDoc.toDTO(doc);
    }

    // ISSUE #15
    public DocumentoDTO getDocumento(Long id) throws IOException {
        DocumentoDTO docDTO = mapperDoc.toDTO(documentoRepository.findById(id).get());
        docDTO.getArquivos().forEach(arq -> {
            // arq.setDados(null);
            arq.setDocumento(null);
        });
        return docDTO;
    }

    public DocumentoDTO criar(DocumentoDTO documento) throws IOException {
        // militarService.getByMatricula(documento.getCriador().getMatricula());
        Documento docModel = mapperDoc.toModel(documento);
        docModel.setCriado(new Date());
        docModel.setAtualizado(new Date());
        docModel = documentoRepository.save(docModel);
        return mapperDoc.toDTO(docModel);
    }

    public List<DocumentoDTO> getAllDocumentos() throws IOException {
        List<DocumentoDTO> list = documentoRepository.findAll().stream().map(documento -> {
            DocumentoDTO docTemp = mapperDoc.toDTO(documento);
            docTemp.setArquivos(null);
            return docTemp;
        }).collect(Collectors.toList());
        return list;
    }

}

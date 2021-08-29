package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.mappers.DocumentoMapper;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.repository.specifications.DocumentoSpecification;
import com.digitalse.cbm.back.responseFiles.RFBuscaDocumentos;
import com.digitalse.cbm.back.responseFiles.RFDocumento;
import com.digitalse.cbm.back.responseFiles.RFEditarDocumento;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    /**
     * Cria um novo documento
     * 
     * @param documento DTO do documento a ser salvo
     * @return retorna um response file do documento salvo
     * @throws IOException
     * @throws HttpException
     */
    public RFDocumento criar(DocumentoDTO documentodto) throws IOException, HttpException {
        if (!documentodto.isValidationOk()) throw new HttpException("validation failed");

        Documento docModel = DocumentoMapper.toModel(documentodto);
        RFDocumento rfdoc = new RFDocumento(documentoRepository.save(docModel));
        return rfdoc;
    }

    /**
     * Edita um documento e salva no banco
     * 
     * @param id id do documento a ser editado
     * @param documentodto dto com documento atualizado
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public RFEditarDocumento editar(long id, DocumentoDTO documentodto) throws IOException, ValidationException, HttpException {
        if (!documentodto.isValidationOk()) throw new HttpException("erro");

        Documento doc = documentoRepository.findById(id).get();

        doc.setTipo(documentodto.getTipo());
        doc.setNumeracao(documentodto.getNumeracao());
        doc.setNome(documentodto.getNome());
        doc.setDescricao(documentodto.getDescricao());
        doc.setData(documentodto.getData());
        doc.setMilitares(documentodto.getMilitares());
        doc.setPublico(documentodto.getPublico());
        documentoRepository.save(doc);
        return new RFEditarDocumento(doc);
    }

    /**
     * Busca um documento pelo id
     * 
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
     * 
     * @param docJson jsonNode gerada com os campos de busca
     * @return uma lista de responsefiles de documentos
     * @throws IOException
     */
    public List<RFBuscaDocumentos> getDocumento(Map<String, Object> map) throws IOException {
        DocumentoSpecification ds = new DocumentoSpecification(map);

        List<RFBuscaDocumentos> rfDocList = documentoRepository.findAll(ds).stream().map(documento -> {
            RFBuscaDocumentos newRFDoc = new RFBuscaDocumentos(documento);
            return newRFDoc;
        }).collect(Collectors.toList());
        /*
         * List<RFBuscaDocumentos> rfDocList =
         * documentoRepository.findAll().stream().map(documento -> { RFBuscaDocumentos
         * newRFDoc = new RFBuscaDocumentos(documento); return newRFDoc;
         * }).collect(Collectors.toList());
         */
        return rfDocList;
    }
}

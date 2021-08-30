package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import com.digitalse.cbm.back.DTO.DTOsDocumento.DocumentoDTO;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.entities.Militar;
import com.digitalse.cbm.back.mappers.DocumentoMapper;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.repository.MilitarRepository;
import com.digitalse.cbm.back.repository.specifications.DocumentoSpecification;
import com.digitalse.cbm.back.responseFiles.RFsDocumento.RFBuscaDocumentos;
import com.digitalse.cbm.back.responseFiles.RFsDocumento.RFDocumento;
import com.digitalse.cbm.back.responseFiles.RFsDocumento.RFEditarDocumento;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private MilitarRepository militarRepository;

    @Autowired
    private MilitarService militarService;

    /**
     * Cria um novo documento
     * 
     * @param documento DTO do documento a ser salvo
     * @return retorna um response file do documento salvo
     * @throws IOException
     * @throws HttpException
     */
    public RFDocumento criar(DocumentoDTO documentodto) throws IOException, HttpException {
        if (!documentodto.isValidationOk())
            throw new HttpException("validation failed");

        RFDocumento rfdoc;
        Documento doc = DocumentoMapper.toModel(documentodto);
        doc.setMilitares(null);

        List<Militar> militares = new ArrayList<Militar>();

        if (documentodto.getMilitares() != null) {
            documentodto.getMilitares().forEach(element -> {
                if (!militarRepository.existsByMatricula(element.getMatricula())) {
                    try {
                        if (militarService.getListByMatricula(element.getMatricula()) != null) {
                            Militar mil = new Militar(element.getMatricula(), new ArrayList<Documento>());
                            mil = militarRepository.save(mil);
                            militares.add(mil);
                        } else {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Envie um militar valido");
                        }

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {

                    Militar mil = militarRepository.findByMatricula(element.getMatricula()).get();
                    militares.add(mil);

                }
            });
        }

        doc.setMilitares(militares);
        rfdoc = new RFDocumento(documentoRepository.save(doc));

        return rfdoc;
    }

    /**
     * Edita um documento e salva no banco
     * 
     * @param id           id do documento a ser editado
     * @param documentodto dto com documento atualizado
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public RFEditarDocumento editar(long id, DocumentoDTO documentodto)
            throws IOException, ValidationException, HttpException {
        if (!documentodto.isValidationOk())
            throw new HttpException("erro");

        Documento doc = documentoRepository.findById(id).get();

        doc.setTipo(documentodto.getTipo());
        doc.setNumeracao(documentodto.getNumeracao());
        doc.setNome(documentodto.getNome());
        doc.setDescricao(documentodto.getDescricao());
        doc.setData(documentodto.getData());
        doc.setPublico(documentodto.getPublico());

        List<Militar> militares = new ArrayList<Militar>();

        if (documentodto.getMilitares() != null) {
            documentodto.getMilitares().forEach(element -> {
                if (!militarRepository.existsByMatricula(element.getMatricula())) {
                    Militar mil = new Militar(element.getMatricula(), new ArrayList<Documento>());
                    mil = militarRepository.save(mil);
                    militares.add(mil);
                } else {
                    Militar mil = militarRepository.findByMatricula(element.getMatricula()).get();
                    militares.add(mil);
                }
            });
        }

        doc.setMilitares(militares);
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

package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.digitalse.cbm.back.DTO.DTOsDocumento.DocumentoDTO;
import com.digitalse.cbm.back.DTO.DTOsMilitar.MilitarDTO;
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
import org.springframework.stereotype.Service;

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
     * @throws Exception
     * @throws IOException
     * @throws HttpException
     */
    public RFDocumento addDocument(DocumentoDTO documentodto) throws Exception {
        if (validateDTO(documentodto) == false)
            throw new Exception("Documento não passou na validação");
        documentodto.setId(null);
        Documento doc = DocumentoMapper.toModel(documentodto);
        doc.setMilitares(new ArrayList<Militar>());
        if (documentodto.getMilitares() != null) {
            // TODO: Aprimorar query
            documentodto.getMilitares().forEach(element -> {
                doc.getMilitares().add(militarRepository.findByMatricula(element.getMatricula()).get());
            });
        }
        RFDocumento rfdoc = new RFDocumento(documentoRepository.save(doc));

        return rfdoc;
    }

    /**
     * Edita um documento e salva no banco
     * 
     * @param id           id do documento a ser editado
     * @param documentodto dto com documento atualizado
     * @return
     * @throws Exception
     */
    public RFEditarDocumento updateDocument(DocumentoDTO documentodto) throws Exception {
        if (validateDTO(documentodto) == false)
            throw new Exception("Documento não passou na validação");

        Documento doc = documentoRepository.findById(documentodto.getId()).get();
        doc.setTipo(documentodto.getTipo());
        doc.setNumeracao(documentodto.getNumeracao());
        doc.setNome(documentodto.getNome());
        doc.setDescricao(documentodto.getDescricao());
        doc.setData(documentodto.getData());
        doc.setPublico(documentodto.getPublico());

        // TODO: Aprimorar query
        documentodto.getMilitares().forEach(element -> {
            doc.getMilitares().add(militarRepository.findByMatricula(element.getMatricula()).get());
        });

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

    public List<RFBuscaDocumentos> getDocument(String nome, String tipo, String numeracao, LocalDate dataInicial,
            LocalDate dataFinal, String matriculaMilitar, String nomeMilitar, String palavras) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if (nome != null)
            if (!nome.isBlank())
                map.put("nome", nome);
        if (tipo != null)
            if (!tipo.isBlank())
                map.put("tipo", tipo);
        if (numeracao != null)
            if (!numeracao.isBlank())
                map.put("numeracao", numeracao);
        if (dataInicial != null)
            map.put("dataInicial", dataInicial);
        if (dataFinal != null)
            map.put("dataFinal", dataFinal);
        if (matriculaMilitar != null)
            map.put("matriculaMilitar", dataFinal);
        if (nomeMilitar != null)
            map.put("nomeMilitar", dataFinal);
        if (palavras != null)
            map.put("palavras", dataFinal);

        DocumentoSpecification ds = new DocumentoSpecification(map);

        List<RFBuscaDocumentos> rfDocList = documentoRepository.findAll(ds).stream().map(documento -> {
            RFBuscaDocumentos newRFDoc = new RFBuscaDocumentos(documento);
            return newRFDoc;
        }).collect(Collectors.toList());

        return rfDocList;
    }

    public void deleteDocument(long id) throws IOException {

    }

    // VALIDAÇÕES

    public boolean validateDTO(DocumentoDTO documentodto) throws Exception {
        boolean valid = true;
        if (validateTipo(documentodto.getTipo()) == false)
            valid = false;
        if (validateMilitar(documentodto.getMilitares()) == false)
            valid = false;
        return valid;
    }

    public boolean validateTipo(String tipo) throws Exception {
        List<String> tipos = Arrays.asList("bga", "bgo", "bir");
        if (!tipos.contains(tipo))
            return false;
        else
            return true;
    }

    public boolean validateMilitar(List<MilitarDTO> militares) throws IOException {
        if (militares == null)
            return true;
        for (MilitarDTO element : militares) {
            if (!militarService.militarExists(element)) {
                return false;
            }
        }
        return true;
    }

}

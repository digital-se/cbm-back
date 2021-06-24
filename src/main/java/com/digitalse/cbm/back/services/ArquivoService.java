package com.digitalse.cbm.back.services;

import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.models.Arquivo;
import com.digitalse.cbm.back.models.Documento;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private DocumentoService documentoService;


    // public List<Arquivo> cadastrar(long docId, List<Arquivo> arquivos) {
    //     Documento documento = documentoService.getDocumento(docId);
    //     arquivos.forEach(arquivo -> {
    //         arquivo = ArquivoDTO.convertFromModel(arquivoRepository.save(Arquivo.convertFromDTO(arquivo))) ;
    //     });
    //     return arquivos;
    // }

    public List<ArquivoDTO> listar(long id) {
        List<ArquivoDTO> lista = new ArrayList<>();
        documentoRepository.findById(id).get().getArquivos().forEach(arquivo -> lista.add(ArquivoDTO.convertFromModel(arquivo)));
        return lista;
    }

    // public List<ArquivoDTO> info(long idDocumento, long idArquivo) {
    //     List<ArquivoDTO> lista = new ArrayList<>();
    //     documentoRepository.findById(id).get().getArquivos().forEach(arquivo -> lista.add(ArquivoDTO.convertFromModel(arquivo)));
    //     return lista;
    // }
}

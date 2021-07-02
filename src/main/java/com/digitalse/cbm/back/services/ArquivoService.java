package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.repository.ArquivoRepository;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private ArquivoRepository arquivoRepository;

    public List<Arquivo> addAllArchives(long documento_id, LinkedList<ArquivoDTO> arquivosDTO,
            LinkedList<MultipartFile> files) throws IOException {
        List<Arquivo> arquivos = new ArrayList<>();

        Documento doc = documentoService.getDocumento(documento_id);

        while (!arquivosDTO.isEmpty()) {
            ArquivoDTO tempArq = arquivosDTO.removeFirst();
            MultipartFile tempFile = files.removeFirst();

            Arquivo finalArq = new Arquivo(tempArq.getId(), doc, tempFile.getOriginalFilename(), tempArq.getOcr(),
                    tempArq.getStatus(), tempFile.getContentType(), tempFile.getSize(), tempFile.getBytes(),
                    tempArq.getTexto(), tempArq.getCriado(), tempArq.getAtualizado());

            System.out.println(finalArq.toString());

            arquivos.add(arquivoRepository.save(finalArq));
        }

        return arquivos;
    }

    // public List<ArquivoDTO> listar(long id) {
    // List<ArquivoDTO> lista = new ArrayList<>();
    // documentoRepository.findById(id).get().getArquivos()
    // .forEach(arquivo -> lista.add(ArquivoDTO.convertFromModel(arquivo)));
    // return lista;
    // }

    // public List<ArquivoDTO> info(long idDocumento, long idArquivo) {
    // List<ArquivoDTO> lista = new ArrayList<>();
    // documentoRepository.findById(id).get().getArquivos().forEach(arquivo ->
    // lista.add(ArquivoDTO.convertFromModel(arquivo)));
    // return lista;
    // }
}

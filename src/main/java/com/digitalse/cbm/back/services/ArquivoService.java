package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Documento;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private DocumentoService documentoService;

    public List<Arquivo> addAllArchives(long documento_id, LinkedList<ArquivoDTO> arquivosDTO,
            LinkedList<MultipartFile> files) throws IOException {
        Documento doc = documentoService.getDocumento(documento_id);
        while (!arquivosDTO.isEmpty()) {
            ArquivoDTO tempArq = arquivosDTO.removeFirst();
            MultipartFile tempFile = files.removeFirst();

            // new Arquivo(id, documento, nome, ocr, status, mime, tamanho, dados, criado,
            // atualizado)
            Arquivo finalArq = new Arquivo(tempArq.getId(), doc, tempArq.getNome(), tempArq.getOcr(),
                    tempArq.getStatus(), tempFile.getContentType(), tempFile.getSize(),
                    ArrayUtils.toObject(tempFile.getBytes()), tempArq.getTexto(), tempArq.getCriado(),
                    tempArq.getAtualizado());

            doc.getArquivos().add(finalArq);
        }

        documentoService.salvar(doc);

        List<Arquivo> arquivos = doc.getArquivos().stream().map(arquivo -> {
            return new Arquivo(arquivo.getId(), /* doc */null, arquivo.getNome(), arquivo.getOcr(),
            arquivo.getStatus(), arquivo.getMime(), arquivo.getTamanho(), /* dados */null, /* texto */null,arquivo.getCriado(),
            arquivo.getAtualizado());
        }).collect(Collectors.toList());
        
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

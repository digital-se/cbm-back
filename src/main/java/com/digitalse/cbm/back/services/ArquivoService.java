package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.mappers.ArquivoMapper;
import com.digitalse.cbm.back.models.Arquivo;
import com.digitalse.cbm.back.models.Documento;
import com.digitalse.cbm.back.repository.DocumentoRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private ArquivoMapper mapper = Mappers.getMapper(ArquivoMapper.class);

    public List<ArquivoDTO> addAllArchives(long documento_id, LinkedList<ArquivoDTO> arquivosDTO,
            LinkedList<MultipartFile> files) throws IOException {
        Documento doc = documentoService.getDocumento(documento_id);
        while (!arquivosDTO.isEmpty()) {
            ArquivoDTO tempArq = arquivosDTO.removeFirst();
            MultipartFile tempFile = files.removeFirst();

            Arquivo finalArq = new Arquivo(tempArq.getArquivo_id(), doc, tempArq.getCriador(), tempArq.getDataHoraCadastro(),
                    tempArq.getStatus(), tempArq.getNoOcr(), tempFile);

            doc.getArquivos().add(finalArq);
        }

        documentoService.salvar(doc);

        List<Arquivo> arquivos = new ArrayList<>();
        doc.getArquivos().forEach(arquivo -> {
            arquivos.add(new Arquivo(arquivo.getArquivo_id(), null, arquivo.getNome(), arquivo.getTipo(), arquivo.getCriador(), 
            arquivo.getDataHoraCadastro(), arquivo.getStatus(), arquivo.getNoOcr(), arquivo.getTamanho()));
        });

        return mapper.toDTO(arquivos);
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

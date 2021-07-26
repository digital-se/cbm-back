package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Bucket;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.mappers.ArquivoMapper;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.responseFiles.RFCriarArquivo;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private ArquivoMapper mapperArq = Mappers.getMapper(ArquivoMapper.class);

    public List<RFCriarArquivo> addAllArchives(long documento_id, LinkedList<RFCriarArquivo> arquivosRF,
            LinkedList<MultipartFile> files) throws IOException {
        List<RFCriarArquivo> returnRF = new ArrayList<>();

        Documento doc = documentoRepository.findById(documento_id).get();

        while (!returnRF.isEmpty()) {
            RFCriarArquivo tempArq = arquivosRF.removeFirst();
            MultipartFile tempFile = files.removeFirst();

            Bucket bucket = new Bucket(null, tempFile.getOriginalFilename(), tempFile.getContentType(),
                    tempFile.getSize(), tempFile.getBytes(), null, null);
            bucket = bucketRepository.save(bucket);

            Arquivo finalArq = new Arquivo(tempArq.getId(), doc, tempFile.getOriginalFilename(), tempArq.getOcr(),
                    tempArq.getStatus(), tempArq.getTexto(), bucket.getId(), null,
                    null);
            arquivoRepository.save(finalArq);
            returnRF.add(tempArq);
        }

        return returnRF;
    }

    public Bucket getFile(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        
        return bucketRepository.findById(arq.getId()).get();
    }

    public ArquivoDTO findArchive(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        return mapperArq.toDTO(arq);
    }

    public List<ArquivoDTO> getArchivesFromDocument(long documento_id) {
        Documento doc = documentoRepository.findById(documento_id).get();
        List<ArquivoDTO> list = mapperArq.toDTO(doc.getArquivos());
        list.forEach(arq -> {
            arq.setDocumento(null);
        });
        return list;
    }

    public ArquivoDTO editar(long id, ArquivoDTO newArquivo/* , MultipartFile newFile */) throws IOException {
        Arquivo arq = arquivoRepository.findById(id).get();
        arq.setOcr(newArquivo.getOcr());
        arq.setStatus(newArquivo.getStatus());
        
        arq.setTexto(newArquivo.getTexto());
        arq.setAtualizado(new Date());
        arquivoRepository.save(arq);
        return mapperArq.toDTO(arquivoRepository.findById(id).get());
    }

    public void deletarArquivo(long id) {
        arquivoRepository.deleteById(id);
    }
}

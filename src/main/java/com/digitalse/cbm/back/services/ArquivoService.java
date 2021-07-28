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
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.responseFiles.RFArquivo;
import com.digitalse.cbm.back.responseFiles.RFBucket;
import com.digitalse.cbm.back.responseFiles.RFCriarArquivo;

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

    public List<RFCriarArquivo> addAllArchives(long documento_id, LinkedList<ArquivoDTO> arquivosRF,
            LinkedList<MultipartFile> files) throws IOException {
        List<RFCriarArquivo> returnRF = new ArrayList<>();

        Documento doc = documentoRepository.findById(documento_id).get();

        while (!arquivosRF.isEmpty()) {
            ArquivoDTO tempArq = arquivosRF.removeFirst();
            MultipartFile tempFile = files.removeFirst();

            Bucket bucket = new Bucket(null, tempFile.getOriginalFilename(), tempFile.getContentType(),
                    tempFile.getSize(), tempFile.getBytes(), null, null);
            bucket = bucketRepository.save(bucket);

            Arquivo finalArq = new Arquivo(tempArq.getId(), doc, tempFile.getOriginalFilename(), tempArq.getOcr(),
                    tempArq.getStatus(), tempArq.getTexto(), bucket.getId(), null,
                    null);
            RFCriarArquivo rfarq = new RFCriarArquivo(arquivoRepository.save(finalArq));
            returnRF.add(rfarq);
        }

        return returnRF;
    }

    /* Retorna a imagem do bucket */
    public RFBucket getBucket(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        Bucket bucket = bucketRepository.findById(arq.getBucket()).get();
        return new RFBucket(bucket);
    }

    /* Retorna a infomração do arquivo */
    public RFArquivo getArquivo(long arquivo_id) {
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        return new RFArquivo(arq);
    }

    public List<RFArquivo> getArchivesFromDocument(long documento_id) {
        List<RFArquivo> list = new ArrayList<>();

        Documento doc = documentoRepository.findById(documento_id).get();
        doc.getArquivos().forEach(arq -> {
            list.add(new RFArquivo(arq));
        });

        return list;
    }

    public RFArquivo editar(long id, ArquivoDTO newArquivo/* , MultipartFile newFile */) throws IOException {
        Arquivo arq = arquivoRepository.findById(id).get();
        arq.setOcr(newArquivo.getOcr());
        arq.setStatus(newArquivo.getStatus());
        
        arq.setTexto(newArquivo.getTexto());
        arq.setAtualizado(new Date());
        arquivoRepository.save(arq);
        return new RFArquivo(arq);
    }

    public void deletarArquivo(long id) {
        arquivoRepository.deleteById(id);
    }
}

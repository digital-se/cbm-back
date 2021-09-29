package com.digitalse.cbm.back.services;

import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoDTO;
import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoEditarDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Bucket;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.repository.DocumentoRepository;
import com.digitalse.cbm.back.responseFiles.RFsArquivo.RFArquivo;
import com.digitalse.cbm.back.responseFiles.RFsArquivo.RFCriarArquivo;
import com.digitalse.cbm.back.responseFiles.RFsBucket.RFBucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

    @Autowired
    private OcrService ocrService;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private BucketRepository bucketRepository;


    //========= V2 ============
    /**
     * 
     * @param documento_id
     * @param arquivodto
     * @param file
     * @return
     * @throws Exception
     */
    public RFCriarArquivo addFile(long documento_id, ArquivoDTO arquivodto, MultipartFile file) throws Exception {
        Documento documento = documentoRepository.findById(documento_id).get();

        Bucket bucket = new Bucket(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                file.getBytes());
        bucket = bucketRepository.save(bucket);

        Arquivo finalArq = new Arquivo(documento, file.getOriginalFilename(), arquivodto.getOcr(),
                bucket.getId());

        RFCriarArquivo responseFile = new RFCriarArquivo(arquivoRepository.save(finalArq));
        ocrService.updateOcr();
        return responseFile;
    }

    /**
     * 
     * @param documento_id
     * @return
     */
    public List<RFArquivo> listFile(Long documento_id){
        List<RFArquivo> list = new ArrayList<>();

        Documento doc = documentoRepository.findById(documento_id).get();
        doc.getArquivos().forEach(arq -> {
            list.add(new RFArquivo(arq));
        });

        return list;
    }

    /**
     * 
     * @param arquivo_id
     * @return
     */
    public RFArquivo getFile(Long arquivo_id){
        Arquivo arq = arquivoRepository.findById(arquivo_id).get();
        return new RFArquivo(arq);
    }

    /**
     * 
     * @param documento_id
     * @param arquivodto
     * @return
     */
    public RFArquivo updateFile(long documento_id, ArquivoEditarDTO arquivodto){
        Arquivo arq = arquivoRepository.findById(arquivodto.getId()).get();

        if(arquivodto.getOcr() != arq.getOcr()){
            if(arquivodto.getOcr() == true){
                arq.setOcr(arquivodto.getOcr());
                arq.setStatus(Arquivo.status_processando);
                arq.setTexto(null);
            } else {
                arq.setOcr(arquivodto.getOcr());
                arq.setStatus(Arquivo.status_concluido);
                arq.setTexto(null);
            }
        } else {
            if(arquivodto.getTexto() != arq.getTexto()){
                arq.setTexto(arquivodto.getTexto());
            }
        }

        arquivoRepository.save(arq);
        return new RFArquivo(arq);
    }

    /**
     * 
     * @param arquivo_id
     */
    public void deleteFile(Long arquivo_id){
        arquivoRepository.deleteById(arquivo_id);
    }

    /**
     * 
     * @param arquivo_id
     * @return
     */
    public RFBucket getFileRaw(Long arquivo_id){
        Arquivo arquivo = arquivoRepository.findById(arquivo_id).get();
        Bucket bucket = bucketRepository.findById(arquivo.getBucket()).get();
        return new RFBucket(bucket);
    }
}

package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsBucket.BucketOcrDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Bucket;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.responseFiles.RFBucketOcr;
import com.digitalse.cbm.back.services.utils.OcrHttpConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OcrService {

    @Autowired
    ArquivoRepository arqRepo;

    @Autowired
    BucketRepository bucRepo; 

    @Autowired
    OcrHttpConnection ocrConnection;
    
    @Async
    public void updateOcr() throws IOException {
        ocrConnection.sendId(getImageIds());
    }

    public List<Long> getImageIds() {
        List<Long> idsPorCriado = arqRepo.findOrderingCriado().get();
        System.out.println(idsPorCriado);
        return idsPorCriado;
    }

    public BucketOcrDTO sendImageToScan(Long arquivo_id) throws IOException{
        Long bucket_id = arqRepo.findById(arquivo_id).get().getBucket();
        Bucket bucket = bucRepo.findById(bucket_id).get();
        //Sent via http
        return new BucketOcrDTO(bucket.getId(), arquivo_id, bucket.getNome(), bucket.getMime(),
        bucket.getTamanho(), bucket.getDados());
    }

    public void saveScannedText(RFBucketOcr rfBucketOcr) {
        Arquivo arquivo = arqRepo.findById(rfBucketOcr.getArquivo_id()).get();
        arquivo.setTexto(rfBucketOcr.getTexto());
        arquivo.setStatus("Processado");
        arqRepo.save(arquivo);
    }

    /* @Autowired
    private RabbitTemplate rt; */

    //Rabbit
    /* public void sendImage(String nomeFila, BucketDTO file) throws AmqpException, IOException {
        rt.convertAndSend(nomeFila, file);
    } */

    // Utils
    /* public byte[] serialize(BucketDTO obj) throws IOException {
        RFBucket ocrb = new RFBucket(obj.getId(), obj.getNome(), obj.getMime(), obj.getTamanho(), obj.getDados(),null,null);

        byte[] base64Object = new byte[] {};
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(ocrb);
            so.flush();
            base64Object = Base64.getEncoder().encode(bo.toByteArray());
        } catch (Exception e) {
            System.out.println(e);
        }
        return base64Object;
    }

    // Utils
    public RFBucket deserialize(byte[] base64Object) throws IOException {
        RFBucket obj = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Object);
            // byte b[] = serializedObject.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(decodedBytes);
            ObjectInputStream si = new ObjectInputStream(bi);
            obj = (RFBucket) si.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return obj;
    } */

}

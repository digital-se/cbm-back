package com.digitalse.cbm.back.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.digitalse.cbm.back.DTO.BucketOcrDTO;
import com.digitalse.cbm.back.DTO.DTOsBucket.BucketDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.repository.ArquivoRepository;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.responseFiles.RFBucketOcr;
import com.digitalse.cbm.back.responseFiles.RFsBucket.RFBucket;
import com.google.gson.Gson;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrService {

    @Autowired
    ArquivoRepository arqRepo;

    @Autowired
    BucketRepository bucRepo;

    @Autowired
    private RabbitTemplate rt;

    //Rabbit
    public void sendImage(String nomeFila, BucketDTO file) throws AmqpException, IOException {
        rt.convertAndSend(nomeFila, file);

    }

    //Rabbit
    public void testeRabbit(MultipartFile obj) throws AmqpException, IOException {
        /* rs.publishMessage(serialize(obj)); */
    }

    //Paliativo http
    public void sendToOcr(BucketOcrDTO file) throws IOException {
        String url = "http://localhost:9083/ocr/extrair";

        URL UrlObj = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            byte[] input = new Gson().toJson(file).getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        System.out.println(
                "Sent 'HTTP POST' request to ocr on : " + url + " .  Response Code : " + connection.getResponseCode());
    }

    //Paliativo http
    public void receiveFromOcr(RFBucketOcr rfBucketOcr) {
        Arquivo arquivo = arqRepo.findById(rfBucketOcr.getArquivo_id()).get();
        arquivo.setTexto(rfBucketOcr.getTexto());
        arquivo.setStatus("Processado");
        arqRepo.save(arquivo);
    }

    // Utils
    public byte[] serialize(BucketDTO obj) throws IOException {
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
    }

}

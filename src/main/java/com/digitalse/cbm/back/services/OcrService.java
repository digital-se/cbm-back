package com.digitalse.cbm.back.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import com.digitalse.cbm.back.responseFiles.RFOcrBucket;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrService {

    @Autowired
    private RabbitService rs = new RabbitService();
    
    public void testeRabbit(MultipartFile obj) throws AmqpException, IOException{
        rs.publishMessage(serialize(obj));
    }
    
    /* public RFOcrBucket sendAmqp(MultipartFile file) throws IOException {
        //Channel channel = ctx.getBean("rabbitConnectionFactory"). connectionFactory.createChannel();
        byte[] serializedObject  = new byte[]{};
        serializedObject = serialize(file);
        rt.send(new Message(serializedObject));
        return deserialize(serializedObject);
    } */

    public byte[] serialize(MultipartFile obj) throws IOException {
        RFOcrBucket ocrb = new RFOcrBucket(0L, obj.getOriginalFilename(), obj.getContentType(), obj.getSize(), obj.getBytes());
        

        byte[] base64Object = new byte[]{};
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

    public RFOcrBucket deserialize(byte[] base64Object) throws IOException {
        RFOcrBucket obj = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Object);
            //byte b[] = serializedObject.getBytes(); 
            ByteArrayInputStream bi = new ByteArrayInputStream(decodedBytes);
            ObjectInputStream si = new ObjectInputStream(bi);
            obj = (RFOcrBucket) si.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return obj;
    }
    

    /**
     * EM TESTES!!! INCOMPLETO Envia uma imagem para o OCR (Microsserviço do
     * digital-se)
     * 
     * @param file MultipartFile da imagem
     * @return
     * @throws IOException
     */
    public MultiValueMap<String, String> send(MultipartFile file) throws IOException {

        // converte arquivo para formato enviavel
        ByteArrayResource fileConvertido = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // seta o media type para o correto (opcional, não faz diferença na pratica)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // define um tipo de "json" para enviar os dados
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileConvertido);

        // cria entidade de resposta (opcional, não faz diferença na pratica)
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // envia um post para o serviço do ocr e recebe a resposta
        RestTemplate restTemplate = new RestTemplate();

        // retorna a resposta do ocr (precisa mudar pro formato la de
        // ResponseEntity.ok(resultado);
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:9191/ocr/extrair", requestEntity,
                String.class);

        MultiValueMap<String, String> response = new LinkedMultiValueMap<>();

        response.add("txt", result.getBody());

        return response;
    }
}

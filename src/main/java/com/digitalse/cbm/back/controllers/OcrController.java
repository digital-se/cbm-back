package com.digitalse.cbm.back.controllers;

import java.io.IOException;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsBucket.BucketOcrDTO;
import com.digitalse.cbm.back.repository.BucketRepository;
import com.digitalse.cbm.back.responseFiles.RFBucketOcr;
import com.digitalse.cbm.back.services.OcrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
public class OcrController {

    @Autowired
    public OcrService ocr = new OcrService();

    @Autowired
    public BucketRepository bucRepo;

    // PostToBack
    @PostMapping("/ocr/receberDados")
    public ResponseEntity<String> receber(@RequestBody RFBucketOcr file) throws RestClientException, IOException {
        try {
            System.out.println(file.getTexto());
            ocr.saveScannedText(file);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    // GetListFromBack
    @GetMapping("/ocr/imagem/list")
    public ResponseEntity<List<Long>> getListOfImagem() throws RestClientException, IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ocr.getImageIds());
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    // GetImageFromBack
    @GetMapping("/ocr/imagem/{id_image}")
    public ResponseEntity<BucketOcrDTO> getImagem(@PathVariable Long id_image) throws RestClientException, IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ocr.sendImageToScan(id_image));
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    /*
     * // RabbitMQ Implementation
     * 
     * @PostMapping("/ocr/testes2") public ResponseEntity<InputStreamResource>
     * enviarImagem(@RequestParam(name = "file") MultipartFile file) throws
     * RestClientException, IOException { try { // RFOcrBucket ocrbucket =
     * ocr.sendAmqp(file); ocr.sendImage(RabbitMQConnection.NOME_QUEUE_OCR, new
     * BucketDTO(0L, file.getOriginalFilename(), file.getContentType(),
     * file.getSize(), file.getBytes())); return
     * ResponseEntity.status(HttpStatus.OK).build();
     * 
     * } catch (NullPointerException e) { System.out.println(e); return
     * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } catch (Exception e) {
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
     * e.getLocalizedMessage()); } }
     */

    /*
     * @CrossOrigin(origins = "http://localhost:3000")
     * 
     * @PatchMapping("/documentos/{id}/arquivos/{id}/ocr") public
     * ResponseEntity<MultiValueMap<String, String>> receber(@RequestParam(name =
     * "file") MultipartFile file) throws RestClientException, IOException { try {
     * return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build(); } catch
     * (NullPointerException e) { System.out.println(e); return
     * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } catch (Exception e) {
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
     * e.getLocalizedMessage()); } }
     */

}

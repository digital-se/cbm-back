package com.digitalse.cbm.back.services.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OcrHttpUtils {
    private static final String URL_OCR = "http://localhost:9083/ocr/queue/atualizar";

    // private static final String URL_PREPROCESSOR =
    // "http://localhost:9084/preprocessor";
    // Paliativo http ocr post request
    static public void sendId(List<Long> ids) throws IOException {
        //URL UrlObj = new URL(URL_OCR);
        try {

            new RestTemplate().postForObject(URL_OCR, ids, ResponseEntity.class);

            
            /* //rObject(URL_OCR, String.class)
            JsonNode jsonNode = new ObjectMapper().readTree();
            //System.out.println(jsonNode.toString());
            RFMilitar militarFound = new RFMilitar(jsonNode.get("num_matricula").asText(), jsonNode.get("nom_completo").asText(),
                jsonNode.get("nom_guerra").asText(), jsonNode.get("dsc_cargo").asText());
            HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true); */

            /* try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = new Gson().toJson(ids).getBytes("utf-8");
                outputStream.write(input, 0, input.length); */
                System.out.println("Ocr Connection Success");
            /* } */

        } catch (Exception e) {
            System.out.println("Ocr Connection Failed");
        }
    }

}

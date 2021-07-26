package com.digitalse.cbm.back.responseFiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFCriarArquivo {
    
    private Long id;
    private Long documento_id;
    private Boolean ocr = false;
    private String status = "Concluido";
    private byte[] dados;
    private String texto;
    private Long bucket;

}

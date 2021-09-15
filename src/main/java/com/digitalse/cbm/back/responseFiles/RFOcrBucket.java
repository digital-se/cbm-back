package com.digitalse.cbm.back.responseFiles;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RFOcrBucket implements Serializable  {
    private Long id;
    private String nome;
    private String mime;
    private Long tamanho;
    private byte[] dados;
}


    
package com.digitalse.cbm.back.responseFiles;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFOcrBucket implements Serializable  {
    private static final long serialVersionUID = 6128016096756071380L;

    private Long id;
    private String nome;
    private String mime;
    private Long tamanho;
    private byte[] dados;
}


    
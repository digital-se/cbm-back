package com.digitalse.cbm.back.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BucketDTO {

    private Long id;
    private String nome;
    private String mime;
    private Long tamanho;
    private byte[] dados;
    private Date criado;
    private Date atualizado;
    
}

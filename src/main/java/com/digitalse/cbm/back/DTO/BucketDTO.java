package com.digitalse.cbm.back.DTO;

import java.time.OffsetDateTime;

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
    private OffsetDateTime criado;
    private OffsetDateTime atualizado;
    
}

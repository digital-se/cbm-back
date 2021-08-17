package com.digitalse.cbm.back.responseFiles;

import java.time.OffsetDateTime;

import com.digitalse.cbm.back.entities.Bucket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFBucket {

    private Long id;
    private String nome;
    private String mime;
    private Long tamanho;
    private byte[] dados;
    private OffsetDateTime criado;
    private OffsetDateTime atualizado;

    public RFBucket(Bucket bucket){
        this.id = bucket.getId();
        this.nome = bucket.getNome();
        this.mime = bucket.getMime();
        this.tamanho = bucket.getTamanho();
        this.dados = bucket.getDados();
        this.criado = bucket.getCriado();
        this.atualizado = bucket.getAtualizado();
    }
}

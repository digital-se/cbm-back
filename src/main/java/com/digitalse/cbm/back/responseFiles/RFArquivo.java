package com.digitalse.cbm.back.responseFiles;

import java.time.OffsetDateTime;

import com.digitalse.cbm.back.entities.Arquivo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFArquivo {
    
    private Long id;
    private Long documento_id;
    private Boolean ocr;
    private String status;
    private String texto;
    private Long bucket;
    private OffsetDateTime criado;
    private OffsetDateTime atualizado;

    public RFArquivo(Arquivo arquivo) {
        this.id = arquivo.getId();
        this.documento_id = arquivo.getDocumento().getId();
        this.ocr = arquivo.getOcr();
        this.status = arquivo.getStatus();
        this.texto = arquivo.getTexto();
        this.bucket = arquivo.getBucket();
        this.criado = arquivo.getCriado();
        this.atualizado = arquivo.getAtualizado();
    }

}

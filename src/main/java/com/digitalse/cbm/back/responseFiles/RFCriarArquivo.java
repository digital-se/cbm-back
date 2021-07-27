package com.digitalse.cbm.back.responseFiles;

import java.util.Date;

import com.digitalse.cbm.back.DTO.ArquivoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFCriarArquivo {
    
    private Long id;
    private Long documento_id;
    private Boolean ocr;
    private String status;
    private Long bucket;
    private Date criado;
    private Date atualizado;

    public RFCriarArquivo(ArquivoDTO dto) {
        this.id = dto.getId();
        this.documento_id = dto.getDocumento_id();
        this.ocr = dto.getOcr();
        this.status = dto.getStatus();
        this.bucket = dto.getBucket();
        this.criado = dto.getCriado();
        this.atualizado = dto.getAtualizado();
    }
}

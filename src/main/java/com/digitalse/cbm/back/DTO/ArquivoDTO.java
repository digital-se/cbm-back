package com.digitalse.cbm.back.DTO;

import java.util.Date;

import com.digitalse.cbm.back.entities.Documento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoDTO {

    private Long id;
    private Documento documento;
    private String nome;
    private Boolean ocr = false;
    private String status = "Concluido";
    private String mime;
    private Long tamanho;
    private byte[] dados;
    private String texto;
    private Date criado;
    private Date atualizado;

    public ArquivoDTO(Long id, String nome, Boolean ocr, String status, String mime, Long tamanho, Date criado,
            Date atualizado) {
        this.id = id;
        this.nome = nome;
        this.ocr = ocr;
        this.status = status;
        this.mime = mime;
        this.tamanho = tamanho;
        this.criado = criado;
        this.atualizado = atualizado;
    }

    @Override
    public String toString() {
        return "ArquivoDTO [atualizado=" + atualizado + ", criado=" + criado + ", dados="
                + "rapaz era pra ter mas mt grande..." + ", documento=" + documento + ", id=" + id + ", mime=" + mime
                + ", nome=" + nome + ", ocr=" + ocr + ", status=" + status + ", tamanho=" + tamanho + ", texto=" + texto
                + "]";
    }

}

package com.digitalse.cbm.back.DTO;

import java.util.Arrays;
import java.util.Date;

import com.digitalse.cbm.back.entities.Documento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoDTO {

    private Long id;
    private Documento documento;
    private String nome;
    @NonNull
    private Boolean ocr;
    private String status = "Concluido";
    private String mime;
    private Long tamanho;
    private Byte[] dados;
    private String texto;
    private Date criado;
    private Date atualizado;

    @Override
    public String toString() {
        return "ArquivoDTO [atualizado=" + atualizado + ", criado=" + criado + ", dados=" + Arrays.toString(dados)
                + ", documento=" + documento + ", id=" + id + ", mime=" + mime + ", nome=" + nome + ", ocr=" + ocr
                + ", status=" + status + ", tamanho=" + tamanho + ", texto=" + texto + "]";
    }

}

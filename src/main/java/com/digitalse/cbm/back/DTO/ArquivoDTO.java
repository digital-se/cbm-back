package com.digitalse.cbm.back.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoDTO implements IValidation {

    private Long id;
    private Long documento_id;
    private String nome;
    private Boolean ocr = false;
    private String status = "Concluido"; // Implementar enum
    private String texto;
    private Long bucket;

    public ArquivoDTO(Long id, String nome, Boolean ocr,
            String status) {
        this.id = id;
        this.nome = nome;
        this.ocr = ocr;
        this.status = status;
    }

    @Override
    public boolean isValidationOk() {
        boolean nullStep = false;
        boolean emptyStep = false;
        boolean statusStep = false;

        if (this.id != null && this.documento_id != null && this.nome != null && this.ocr != null && this.status != null
                && this.texto != null && this.bucket != null) {
            nullStep = true;
        }

        if(!nome.isEmpty()) {
            emptyStep = true;
        }

        if(status == "Concluido"){
            statusStep = true;
        }

        if(nullStep == true && emptyStep == true && statusStep == true) return true;
        else return false;
    }

}

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

    private Boolean ocr = false;
    private String status = "Concluido"; // Implementar enum
    private String texto;

    public ArquivoDTO(Boolean ocr, String status) {
        this.ocr = ocr;
        this.status = status;
    }

    @Override
    public boolean isValidationOk() {
        boolean nullStep = false;
        boolean statusStep = false;

        if (this.ocr != null && this.status != null) {
            nullStep = true;
        }

        if (status.contains("Concluido")) {
            statusStep = true;
        }

        if (nullStep == true && statusStep == true)
            return true;
        else
            return false;
    }

}

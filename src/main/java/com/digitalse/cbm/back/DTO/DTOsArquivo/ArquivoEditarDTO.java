package com.digitalse.cbm.back.DTO.DTOsArquivo;

import com.digitalse.cbm.back.DTO.Interfaces.IValidation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoEditarDTO implements IValidation {

    private Boolean ocr = false;
    private String texto;

    @Override
    public boolean isValidationOk() {
        boolean nullStep = false;

        if (this.ocr != null) {
            nullStep = true;
        }

        if (nullStep == true)
            return true;
        else
            return false;
    }

}
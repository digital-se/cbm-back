package com.digitalse.cbm.back.DTO.DTOsArquivo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoEditarDTO {
    private Boolean ocr;
    private String texto;

}
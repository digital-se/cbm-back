package com.digitalse.cbm.back.DTO.DTOsBucket;

import com.digitalse.cbm.back.DTO.Interfaces.IValidation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BucketDTO implements IValidation  {

    private Long id;
    private String nome;
    private String mime;
    private Long tamanho;
    private byte[] dados;

    @Override
    public boolean isValidationOk() {
        boolean nullStep = false;
		boolean emptyStep = false;

		if (this.id != null && this.nome != null && this.mime != null && this.tamanho != null && this.dados != null) {
			nullStep = true;
		}

		if (!nome.isEmpty()) {
			emptyStep = true;
		}

		if (nullStep == true && emptyStep == true)
			return true;
		else
			return false;
	}
    
}

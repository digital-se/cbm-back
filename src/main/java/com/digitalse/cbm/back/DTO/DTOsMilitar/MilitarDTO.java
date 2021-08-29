package com.digitalse.cbm.back.DTO.DTOsMilitar;

import com.digitalse.cbm.back.DTO.Interfaces.IValidation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MilitarDTO  implements IValidation {

    private String matricula;
    private String nome;
    private String nomeDeGuerra;
    private String cargo;

    @Override
    public boolean isValidationOk() {
        boolean nullStep = false;
		boolean emptyStep = false;

		if (this.matricula != null && this.nome != null && this.nomeDeGuerra != null && this.cargo != null) {
			nullStep = true;
		}

		if (!matricula.isEmpty() && !nome.isEmpty() && !nomeDeGuerra.isEmpty() && !cargo.isEmpty()) {
			emptyStep = true;
		}

		if (nullStep == true && emptyStep == true)
			return true;
		else
			return false;
	}

}

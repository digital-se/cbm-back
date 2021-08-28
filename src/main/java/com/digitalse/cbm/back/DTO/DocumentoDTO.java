package com.digitalse.cbm.back.DTO;

import java.time.LocalDate;
import java.util.List;

import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Militar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO implements IValidation {

	private Long id;
	private String nome;
	private String numeracao;
	private Boolean publico;
	private String tipo;
	private LocalDate data;
	private String descricao;
	/*
	 * private OffsetDateTime criado; private OffsetDateTime atualizado;
	 */
	private List<Arquivo> arquivos;
	private List<Militar> militares;

	@Override
	public boolean isValidationOk() {
		boolean nullStep = false;
		//boolean emptyStep = false;
		boolean tipoStep = false;

		if (this.id != null && this.nome != null && this.numeracao != null && this.publico != null && this.tipo != null
				&& this.data != null && this.descricao != null && this.arquivos != null && this.militares != null) {
			nullStep = true;
		}

		/* if (!nome.isEmpty()) {
			emptyStep = true;
		} */

		if (tipo == "bga" || tipo == "bgo" || tipo == "bir") {
			tipoStep = true;
		}

		if (nullStep == true && tipoStep == true)
			return true;
		else
			return false;
	}

}

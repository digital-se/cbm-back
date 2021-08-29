package com.digitalse.cbm.back.DTO.DTOsDocumento;

import java.time.LocalDate;
import java.util.List;

import com.digitalse.cbm.back.DTO.Interfaces.IValidation;
import com.digitalse.cbm.back.entities.Militar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO implements IValidation {
	private String nome;
	private String numeracao;
	private Boolean publico;
	private String tipo;
	private LocalDate data;
	private String descricao;
	private List<Militar> militares;

	@Override
	public boolean isValidationOk() {
		boolean nullStep = false;
		// boolean emptyStep = false;
		boolean tipoStep = false;
		if (this.nome != null && this.numeracao != null && this.publico != null && this.tipo != null
				&& this.data != null && this.descricao != null && this.militares != null) {
			nullStep = true;
		}

		if (tipo.contains("bga") || tipo.contains("bgo") || tipo.contains("bir")) {
			tipoStep = true;
		}

		if (nullStep == true && tipoStep == true) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "DocumentoDTO [data=" + data + ", descricao=" + descricao + ", militares="
				+ militares + ", nome=" + nome + ", numeracao=" + numeracao + ", publico=" + publico + ", tipo=" + tipo
				+ "]";
	}

}

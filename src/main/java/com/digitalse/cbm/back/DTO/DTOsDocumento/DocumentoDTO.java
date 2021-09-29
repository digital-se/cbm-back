package com.digitalse.cbm.back.DTO.DTOsDocumento;

import java.time.LocalDate;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsMilitar.MilitarDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
	private Long id;
	private String nome;
	private String numeracao;
	private Boolean publico;
	private String tipo;
	private LocalDate data;
	private String descricao;
	private List<MilitarDTO> militares;

}

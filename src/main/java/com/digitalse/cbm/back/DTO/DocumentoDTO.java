package com.digitalse.cbm.back.DTO;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Militar;

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
	private OffsetDateTime criado;
	private OffsetDateTime atualizado;
	private List<Arquivo> arquivos;
	private List<Militar> militares;

}

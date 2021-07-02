package com.digitalse.cbm.back.DTO;

import java.util.Date;
import java.util.List;

import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Militar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {

	private Long id;
	private String nome;
	private String numeracao;
	private Boolean publico;
	private String tipo;
	private Date data;
	private String descricao;
	private Date criado;
	private Date atualizado;
	private List<Arquivo> arquivos;
	private List<Militar> militares;

}

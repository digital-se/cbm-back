package com.digitalse.cbm.back.responseFiles;

import java.util.Date;
import java.util.List;

import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.entities.Militar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFBuscaDocumentos {

	private Long id;
	private String nome;
	private String numeracao;
	private Boolean publico;
	private String tipo;
	private Date data;
	private String descricao;
	private Date criado;
	private Date atualizado;
    private List<Militar> militares;

    public RFBuscaDocumentos(Documento doc) {
        this.id = doc.getId();
        this.nome = doc.getNome();
        this.numeracao = doc.getNumeracao();
        this.publico = doc.getPublico();
        this.tipo = doc.getTipo();
        this.data = doc.getData();
        this.descricao = doc.getDescricao();
        this.criado = doc.getCriado();
        this.atualizado = doc.getAtualizado();     
        this.militares = doc.getMilitares();     
    }
}

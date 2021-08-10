package com.digitalse.cbm.back.responseFiles;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.entities.Militar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RFDocumento {
    
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

    public RFDocumento(Documento doc) {
        this.id = doc.getId();
        this.nome = doc.getNome();
        this.numeracao = doc.getNumeracao();
        this.publico = doc.getPublico();
        this.tipo = doc.getTipo();
        this.data = doc.getData();
        this.descricao = doc.getDescricao();
        this.criado = doc.getCriado();
        this.atualizado = doc.getAtualizado();
        this.arquivos = doc.getArquivos();
        this.militares = doc.getMilitares();
         
    }

}

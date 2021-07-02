package com.digitalse.cbm.back.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "documentos")
public class Documento {

	// private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private long documento_id;
	// @Column
	// private String tipo;
	// @Column
	// private String nome;
	// @Column
	// private Date dataHoraCadastro;
	// @Column
	// private Boolean visivel;
	// @Column
	// @JsonManagedReference
	// @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
	// private List<Arquivo> arquivos;
	// @ManyToOne(optional = false)
	// @JoinColumn(name = "criador_id", nullable = false, updatable = false)
	// private Militar criador;
	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name = "Documento_Militar", joinColumns = { @JoinColumn(name =
	// "documento_id") }, inverseJoinColumns = {
	// @JoinColumn(name = "militar_id") })
	// private List<Militar> militares;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Column(nullable = false)
	@Getter
	@Setter
	private String nome;

	@Column(nullable = true)
	@Getter
	@Setter
	private String numeracao;

	@Column(nullable = false)
	@Getter
	@Setter
	private Boolean publico;

	@Column(nullable = false)
	@Getter
	@Setter
	private String tipo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date data;

	@Column(nullable = false)
	@Getter
	@Setter
	private String descricao;

	@Column(nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date criado;

	@Column(nullable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date atualizado;

	@OneToMany(mappedBy = "documento")
	@Getter
	@Setter
	private List<Arquivo> arquivos;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "fk_documento_militar", joinColumns = @JoinColumn(name = "militar_matricula"), inverseJoinColumns = @JoinColumn(name = "documento_id"))
	@Getter
	@Setter
	private List<Militar> militares;

	public Documento() {
	}

	// public Documento(String tipo, String nome, Militar criador, Date
	// dataHoraCadastro, Boolean visivel) {
	// this.tipo = tipo;
	// this.nome = nome;
	// this.criador = criador;
	// this.dataHoraCadastro = dataHoraCadastro;
	// this.visivel = visivel;
	// }

	// public Documento(long documento_id, String tipo, String nome, Militar
	// criador, Date dataHoraCadastro,
	// Boolean visivel, List<Arquivo> arquivos, List<Militar> militares) {
	// this.documento_id = documento_id;
	// this.tipo = tipo;
	// this.nome = nome;
	// this.criador = criador;
	// this.dataHoraCadastro = dataHoraCadastro;
	// this.visivel = visivel;
	// this.arquivos = arquivos;
	// this.militares = militares;
	// }

	// public long getDocumento_id() {
	// return documento_id;
	// }

	// public void setDocumento_id(long documento_id) {
	// this.documento_id = documento_id;
	// }

	// public String getTipo() {
	// return tipo;
	// }

	// public void setTipo(String tipo) {
	// this.tipo = tipo;
	// }

	// public String getNome() {
	// return nome;
	// }

	// public void setNome(String nome) {
	// this.nome = nome;
	// }

	// public Militar getCriador() {
	// return criador;
	// }

	// public void setCriador(Militar criador) {
	// this.criador = criador;
	// }

	// public Date getDataHoraCadastro() {
	// return dataHoraCadastro;
	// }

	// public void setDataHoraCadastro(Date dataHoraCadastro) {
	// this.dataHoraCadastro = dataHoraCadastro;
	// }

	// public Boolean getVisivel() {
	// return visivel;
	// }

	// public void setVisivel(Boolean visivel) {
	// this.visivel = visivel;
	// }

	// public List<Arquivo> getArquivos() {
	// return arquivos;
	// }

	// public void setArquivos(List<Arquivo> arquivos) {
	// this.arquivos = arquivos;
	// }

	// public List<Militar> getMilitares() {
	// return militares;
	// }

	// public void setMilitares(List<Militar> militares) {
	// this.militares = militares;
	// }
}

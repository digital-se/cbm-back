package com.digitalse.cbm.back.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "documentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = true)
	private String numeracao;
	@Column(nullable = false)
	private Boolean publico;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date criado;
	@Column(nullable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date atualizado;
	@OneToMany(mappedBy = "documento")
	@JsonManagedReference
	private List<Arquivo> arquivos;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "fk_documento_militar", joinColumns = @JoinColumn(name = "militar_matricula"), inverseJoinColumns = @JoinColumn(name = "documento_id"))
	private List<Militar> militares;

}

package com.digitalse.cbm.back.entities;

import java.time.LocalDate;
import java.time.OffsetDateTime;
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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "documentos")
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
	private LocalDate data;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	@CreationTimestamp
	private OffsetDateTime criado;
	@Column(nullable = false)
	@UpdateTimestamp
	private OffsetDateTime atualizado;
	@OneToMany(mappedBy = "documento")
	@JsonManagedReference
	private List<Arquivo> arquivos;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "fk_documento_militar", joinColumns = @JoinColumn(name = "militar_matricula"), inverseJoinColumns = @JoinColumn(name = "documento_id"))
	private List<Militar> militares;

}

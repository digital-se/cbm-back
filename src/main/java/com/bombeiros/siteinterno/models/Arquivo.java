package com.bombeiros.siteinterno.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ARQUIVO")
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long arquivo_id;
    @ManyToOne
    @JoinColumn(name = "documento_id", nullable = false)
    private Documento documento;
    @Column
    private String nome;
    @Column
    private String tipo;
    @ManyToOne(optional = false)
	@JoinColumn(name = "criador_id", nullable = false, updatable = false)
	private Militar criador;
    @Column
    private Date dataHoraCadastro;
    @Column
    private String status;
    @Column
    private Boolean noOcr;
    @Column
    private long tamanho;
    @Lob
    private byte[] dados;
    @Lob
    private String texto;

    public Arquivo() {
    }

    public Arquivo(String nome, String tipo, Date dataHoraCadastro) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Arquivo(Long arquivo_id, Documento documento, String nome, String tipo, Militar criador,
            Date dataHoraCadastro, String status, Boolean noOcr, long tamanho) {
        this.arquivo_id = arquivo_id;
        this.documento = documento;
        this.nome = nome;
        this.tipo = tipo;
        this.criador = criador;
        this.dataHoraCadastro = dataHoraCadastro;
        this.status = status;
        this.noOcr = noOcr;
        this.tamanho = tamanho;
    }

    public Arquivo(Long arquivo_id, Documento documento, String nome, String tipo, Militar criador,
            Date dataHoraCadastro, String status, Boolean noOcr, long tamanho, byte[] dados, String texto) {
        this.arquivo_id = arquivo_id;
        this.documento = documento;
        this.nome = nome;
        this.tipo = tipo;
        this.criador = criador;
        this.dataHoraCadastro = dataHoraCadastro;
        this.status = status;
        this.noOcr = noOcr;
        this.tamanho = tamanho;
        this.dados = dados;
        this.texto = texto;
    }

    public Long getArquivo_id() {
        return arquivo_id;
    }

    public void setArquivo_id(Long arquivo_id) {
        this.arquivo_id = arquivo_id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Militar getCriador() {
        return criador;
    }

    public void setCriador(Militar criador) {
        this.criador = criador;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getNoOcr() {
        return noOcr;
    }

    public void setNoOcr(Boolean noOcr) {
        this.noOcr = noOcr;
    }

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    

    

}

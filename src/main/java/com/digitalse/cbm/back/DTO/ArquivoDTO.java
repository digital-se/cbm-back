package com.digitalse.cbm.back.DTO;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.entities.Militar;

import org.springframework.web.multipart.MultipartFile;

public class ArquivoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long arquivo_id;
    private Documento documento;
    private String nome;
    private String tipo;
    private Militar criador;
    private Date dataHoraCadastro;
    private String status;
    private Boolean noOcr;
    private long tamanho;
    private byte[] dados;
    private String texto;

    public ArquivoDTO() {

    }

    public ArquivoDTO(Documento documento, String nome, String tipo, Militar criador, Date dataHoraCadastro,
            String status, Boolean noOcr, long tamanho) {
        this.documento = documento;
        this.nome = nome;
        this.tipo = tipo;
        this.criador = criador;
        this.dataHoraCadastro = dataHoraCadastro;
        this.status = status;
        this.noOcr = noOcr;
        this.tamanho = tamanho;
    }

    // No Post para criar arquivo
    public ArquivoDTO(Documento documento, Militar criador, Date dataHoraCadastro, String status, Boolean noOcr,
            MultipartFile file) throws IOException {
        this.documento = documento;
        this.criador = criador;
        this.dataHoraCadastro = dataHoraCadastro;
        this.status = status;
        this.noOcr = noOcr;
        this.nome = file.getOriginalFilename();
        this.tipo = file.getContentType();
        this.tamanho = file.getSize();
        this.dados = file.getBytes();
    }

    public ArquivoDTO(Long arquivo_id, Documento documento, String nome, String tipo, Militar criador,
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

    public ArquivoDTO(Long arquivo_id, Documento documento, String nome, String tipo, Militar criador,
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

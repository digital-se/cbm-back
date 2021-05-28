package com.bombeiros.siteinterno.DTO;

import java.util.Date;

import com.bombeiros.siteinterno.models.Documento;

public class ArquivoDTO {

    private Long id;
    private Documento documento;
    private String nome;
    private String tipo;
    private Date dataHoraCadastro;
    private String status;
    private Boolean noOcr;
    private long size;
    private byte[] data;
    private String texto;
    
    public ArquivoDTO(Long id, Documento documento, String nome, String tipo, Date dataHoraCadastro, String status,
            Boolean noOcr, long size, byte[] data, String texto) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.tipo = tipo;
        this.dataHoraCadastro = dataHoraCadastro;
        this.status = status;
        this.noOcr = noOcr;
        this.size = size;
        this.data = data;
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}

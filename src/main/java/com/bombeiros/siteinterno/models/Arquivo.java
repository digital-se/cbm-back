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
    @ManyToOne(optional=false) 
    @JoinColumn(name="documento_id", nullable=false, updatable=false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="documento_id", nullable=false)
    private Documento documento;
    @Column
    private String nome;
    @Column
    private String tipo;
    @Column
    private Date dataHoraCadastro;
    @Column
    private String status;
    @Column
    private Boolean noOcr;
    @Column
    private long size;
    @Lob
    private byte[] data;
    @Lob
    private String texto;
    

    

    public Arquivo(String nome, String tipo, Date dataHoraCadastro) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataHoraCadastro = dataHoraCadastro;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
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



    public Documento getDocumento() {
        return documento;
    }



    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    

}

package com.bombeiros.siteinterno.models;

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
@Table(name = "ARQUIVOS")
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column
    private String tipo;
    @Lob
    private byte[] arquivoData;

    @ManyToOne
    @JoinColumn(name = "id_documento", referencedColumnName = "idDocumento")
    private Documento documento;

    public Arquivo() {

    }

    public Arquivo(String name, String type, byte[] arquivoData) {
        this.nome = name;
        this.tipo = type;
        this.arquivoData = arquivoData;
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

    public byte[] getArquivoData() {
        return arquivoData;
    }

    public void setArquivoData(byte[] arquivoData) {
        this.arquivoData = arquivoData;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    

}

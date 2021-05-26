package com.bombeiros.siteinterno.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String tipo;

    @Lob
    private byte[] arquivoData;

    @ManyToOne
    @JoinColumn(name = "id_documento", referencedColumnName = "idDocumento")
    private Document documento;

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

    public Document getDocumento() {
        return documento;
    }

    public void setDocumento(Document documento) {
        this.documento = documento;
    }

    

}

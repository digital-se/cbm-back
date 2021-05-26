package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class BgoDTO {

    private long id_bgo;
    private String nome;
    private int num_bgo;
    private List<ArquivoDTO> documentos;

    public BgoDTO(long id_bgo, String nome, int num_bgo, List<ArquivoDTO> documentos) {
        this.id_bgo = id_bgo;
        this.nome = nome;
        this.num_bgo = num_bgo;
        this.documentos = documentos;
    }

    public BgoDTO(long id_bgo, String nome, int num_bgo) {
        this.id_bgo = id_bgo;
        this.nome = nome;
        this.num_bgo = num_bgo;
    }

    public long getId_bgo() {
        return id_bgo;
    }

    public void setId_bgo(long id_bgo) {
        this.id_bgo = id_bgo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum_bgo() {
        return num_bgo;
    }

    public void setNum_bgo(int num_bgo) {
        this.num_bgo = num_bgo;
    }

}

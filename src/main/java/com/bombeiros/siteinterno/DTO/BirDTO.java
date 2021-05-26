package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class BirDTO {

    private long id_bir;
    private String nome;
    private int num_bir;
    private List<ArquivoDTO> documentos;

    public BirDTO(long id_bir, String nome, int num_bir, List<ArquivoDTO> documentos) {
        this.id_bir = id_bir;
        this.nome = nome;
        this.num_bir = num_bir;
        this.documentos = documentos;
    }

    public BirDTO(long id_bir, String nome, int num_bir) {
        this.id_bir = id_bir;
        this.nome = nome;
        this.num_bir = num_bir;
    }

    public long getId_bir() {
        return id_bir;
    }

    public void setId_bir(long id_bir) {
        this.id_bir = id_bir;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum_bir() {
        return num_bir;
    }

    public void setNum_bir(int num_bir) {
        this.num_bir = num_bir;
    }

}

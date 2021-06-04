package com.bombeiros.back.DTO;

import com.bombeiros.back.models.Militar;

public class MilitarDTO {

    private String numMatricula;
    private String nome;
    private String nomeDeGuerra;
    private String cargo;

    public MilitarDTO() {

    }

    public MilitarDTO(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public MilitarDTO(String numMatricula, String nome, String nomeDeGuerra, String cargo) {
        this.numMatricula = numMatricula;
        this.nome = nome;
        this.nomeDeGuerra = nomeDeGuerra;
        this.cargo = cargo;
    }

    public MilitarDTO(Militar militar) {
        this.numMatricula = militar.getNumMatricula();
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDeGuerra() {
        return nomeDeGuerra;
    }

    public void setNomeDeGuerra(String nomeDeGuerra) {
        this.nomeDeGuerra = nomeDeGuerra;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
}

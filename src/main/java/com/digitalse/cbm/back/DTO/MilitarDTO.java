package com.digitalse.cbm.back.DTO;

import com.digitalse.cbm.back.models.Militar;

public class MilitarDTO {

    private String matricula;
    private String nome;
    private String nomeDeGuerra;
    private String cargo;

    public MilitarDTO() {

    }

    public MilitarDTO(String numMatricula) {
        this.matricula = numMatricula;
    }

    public MilitarDTO(String numMatricula, String nome, String nomeDeGuerra, String cargo) {
        this.matricula = numMatricula;
        this.nome = nome;
        this.nomeDeGuerra = nomeDeGuerra;
        this.cargo = cargo;
    }

    public MilitarDTO(Militar militar) {
        this.matricula = militar.getMatricula();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String numMatricula) {
        this.matricula = numMatricula;
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

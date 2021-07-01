package com.digitalse.cbm.back.DTO;

import com.digitalse.cbm.back.entities.Militar;

public class MilitarDTO {

    private String matricula;
    private String nome;
    private String nomeDeGuerra;
    private String cargo;

    public MilitarDTO() {

    }

    public MilitarDTO(String matricula) {
        this.matricula = matricula;
    }

    public MilitarDTO(String matricula, String nome, String nomeDeGuerra, String cargo) {
        this.matricula = matricula;
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

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

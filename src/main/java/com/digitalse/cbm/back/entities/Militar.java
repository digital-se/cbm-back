package com.digitalse.cbm.back.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.digitalse.cbm.back.DTO.MilitarDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "militares")
public class Militar {

    // private static final long serialVersionUID = 1L;

    // @Id
    // private String matricula;

    // @ManyToMany(mappedBy = "militares", fetch = FetchType.EAGER, cascade =
    // CascadeType.ALL)
    // private List<Documento> documentos;

    @Id
    @Getter
    @Setter
    private String matricula;

    @ManyToMany(mappedBy = "militares")
    @Getter
    @Setter
    private List<Documento> documentos;

    public Militar() {
    }

    // public Militar(String matricula) {
    // this.matricula = matricula;
    // }

    // public String getMatricula() {
    // return matricula;
    // }

    // public void setMatricula(String matricula) {
    // this.matricula = matricula;
    // }

    // public void convertFromDTO(MilitarDTO militar) {
    // this.matricula = militar.getMatricula();
    // }
}

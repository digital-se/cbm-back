package com.digitalse.cbm.back.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.digitalse.cbm.back.DTO.MilitarDTO;

@Entity
@Table(name = "MILITAR")
public class Militar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String matricula;

    @ManyToMany(mappedBy = "militares", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Documento> documentos;

    public Militar() {
    }

    public Militar(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void convertFromDTO(MilitarDTO militar){
        this.matricula = militar.getMatricula();
    }
}

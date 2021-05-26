package com.bombeiros.siteinterno.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

//This class still incomplete
@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idFoto;
    private String name;
    private String type;
    
    @Lob
    private byte[] fotoData;

    @ManyToOne
    @JoinColumn(name = "id_ficha_funcionario", referencedColumnName = "idFichaFuncionario")
    private FichaFuncionario fichaFuncionario;

    public Foto() {
        //do nothing
    }

    public Foto(String name, String type, byte[] fotoData) {
        this.name = name;
        this.type = type;
        this.fotoData = fotoData;
    }

    public Long getId() {
        return idFoto;
    }

    public void setId(Long idFoto) {
        this.idFoto = idFoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFotoData() {
        return fotoData;
    }

    public void setFoto(byte[] fotoData) {
        this.fotoData = fotoData;
    }

    public FichaFuncionario getFichaFuncionario() {
        return fichaFuncionario;
    }

    public void setFichaFuncionario(FichaFuncionario fichaFuncionario) {
        this.fichaFuncionario = fichaFuncionario;
    }

}
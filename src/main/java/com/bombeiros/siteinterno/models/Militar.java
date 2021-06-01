package com.bombeiros.siteinterno.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MILITAR")
public class Militar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String militar_id;

    @ManyToMany(mappedBy = "militares", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Documento> documentos;

    public Militar() {
    }

    public Militar(String militar_id) {
        this.militar_id = militar_id;
    }

    public String getMilitar_id() {
        return militar_id;
    }

    public void setMilitar_id(String militar_id) {
        this.militar_id = militar_id;
    }

}

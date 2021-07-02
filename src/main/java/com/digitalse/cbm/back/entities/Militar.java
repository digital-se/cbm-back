package com.digitalse.cbm.back.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "militares")
@Getter
@Setter
public class Militar {

    @Id
    private String matricula;
    @ManyToMany(mappedBy = "militares")
    private List<Documento> documentos;

    public Militar() {
    }
}

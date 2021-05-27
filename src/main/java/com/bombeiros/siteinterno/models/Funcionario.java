package com.bombeiros.siteinterno.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Funcionario(long id) {
        this.id = id;
    }

    
}

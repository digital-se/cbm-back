package com.bombeiros.siteinterno.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MILITAR")
public class Militar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToMany
    private long militar_id;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getMilitar_id() {
        return militar_id;
    }

    public void setMilitar_id(long militar_id) {
        this.militar_id = militar_id;
    }

    public Militar(long militar_id) {
        this.militar_id = militar_id;
    }

}

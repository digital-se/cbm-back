package com.digitalse.cbm.back.entities;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "arquivos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {

    
    public static final String status_processando = "processando";
    public static final String status_concluido = "concluido";
    
    public boolean validateStatus(String status){
        boolean valid = false;
        if(status.equals(status_processando)) valid = true;
        if(status.equals(status_concluido)) valid = true;
        return valid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    @JsonBackReference
    private Documento documento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean ocr = false;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true, columnDefinition = "text")
    private String texto;

    @Column(nullable = false)
    private Long bucket;

    @Column(nullable = false)
    @CreationTimestamp
    private OffsetDateTime criado;

    @Column(nullable = false)
    @UpdateTimestamp
    private OffsetDateTime atualizado;

    //Criar arquivo
    public Arquivo(Documento documento, String nome, Boolean ocr, Long bucket) throws Exception {
        this.documento = documento;
        this.nome = nome;
        this.ocr = ocr;

        if(ocr == false) this.status = Arquivo.status_concluido;
        else this.status = Arquivo.status_processando;
       
        this.bucket = bucket;
    }

}

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
    @Column(nullable = true)
    private String texto;
    @Column(nullable = false)
    private Long bucket;
    @Column(nullable = false)
    @CreationTimestamp
    private OffsetDateTime criado;
    @Column(nullable = false)
    @UpdateTimestamp
    private OffsetDateTime atualizado;

}

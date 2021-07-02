package com.digitalse.cbm.back.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
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
    private Documento documento;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Boolean ocr = false;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String mime;
    @Column(nullable = false)
    private Long tamanho;
    @Column(nullable = false)
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] dados;
    @Column(nullable = true)
    private String texto;
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date criado;
    @Column(nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizado;

    @Override
    public String toString() {
        return "Arquivo [atualizado=" + atualizado + ", criado=" + criado + ", dados="
                + "rapaz era pra ter mas mt grande..." + ", documento=" + documento + ", id=" + id + ", mime=" + mime
                + ", nome=" + nome + ", ocr=" + ocr + ", status=" + status + ", tamanho=" + tamanho + ", texto=" + texto
                + "]";
    }

}

package com.bombeiros.siteinterno.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


@Entity
public class Imagem {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_imagem;

    private String name;

    private String type;

    @Lob
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "id_bga", referencedColumnName = "id_bga")
    private Bga bga;

    @ManyToOne
    @JoinColumn(name = "id_bgo", referencedColumnName = "id_bgo")
    private Bgo bgo;

    @ManyToOne
    @JoinColumn(name = "id_bir", referencedColumnName = "id_bir")
    private Bir bir;

    @ManyToOne
    @JoinColumn(name = "id_fichaFuncionario", referencedColumnName = "id_fichaFuncionario")
    private FichaFuncionario fichaFuncionario;

    @ManyToOne
    @JoinColumn(name = "id_registroAntigo", referencedColumnName = "id_registroAntigo")
    private RegistroAntigo registroAntigo;

    @ManyToOne
    @JoinColumn(name = "id_relatorioDeProcesso", referencedColumnName = "id_relatorioDeProcesso")
    private RelatorioDeProcesso relatorioDeProcesso;

    
    public Imagem() {

    }
    
    public Long getId_imagem() {
        return id_imagem;
    }

    public void setId_imagem(Long id_imagem) {
        this.id_imagem = id_imagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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

    public Imagem(String name, String type, byte[] imagem) {
        this.name = name;
        this.type = type;
        this.imagem = imagem;
    }

    public Bga getBga() {
        return bga;
    }

    public void setBga(Bga bga) {
        this.bga = bga;
    }

    public Bgo getBgo() {
        return bgo;
    }

    public void setBgo(Bgo bgo) {
        this.bgo = bgo;
    }

    public Bir getBir() {
        return bir;
    }

    public void setBir(Bir bir) {
        this.bir = bir;
    }

    public FichaFuncionario getFichaFuncionario() {
        return fichaFuncionario;
    }

    public void setFichaFuncionario(FichaFuncionario fichaFuncionario) {
        this.fichaFuncionario = fichaFuncionario;
    }

    public RegistroAntigo getRegistroAntigo() {
        return registroAntigo;
    }

    public void setRegistroAntigo(RegistroAntigo registroAntigo) {
        this.registroAntigo = registroAntigo;
    }

    public RelatorioDeProcesso getRelatorioDeProcesso() {
        return relatorioDeProcesso;
    }

    public void setRelatorioDeProcesso(RelatorioDeProcesso relatorioDeProcesso) {
        this.relatorioDeProcesso = relatorioDeProcesso;
    }
    
}

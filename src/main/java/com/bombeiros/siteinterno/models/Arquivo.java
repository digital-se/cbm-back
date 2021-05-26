package com.bombeiros.siteinterno.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDocumento;

    private String name;

    private String type;

    @Lob
    private byte[] documentoData;

    @ManyToOne
    @JoinColumn(name = "id_bga", referencedColumnName = "idBga")
    private Bga bga;

    @ManyToOne
    @JoinColumn(name = "id_bgo", referencedColumnName = "idBgo")
    private Bgo bgo;

    @ManyToOne
    @JoinColumn(name = "id_bir", referencedColumnName = "idBir")
    private Bir bir;

    @ManyToOne
    @JoinColumn(name = "id_ficha_funcionario", referencedColumnName = "idFichaFuncionario")
    private FichaFuncionario fichaFuncionario;

    @ManyToOne
    @JoinColumn(name = "id_registro_antigo", referencedColumnName = "idRegistroAntigo")
    private RegistroAntigo registroAntigo;

    @ManyToOne
    @JoinColumn(name = "id_relatorio_de_processo", referencedColumnName = "idRelatorioDeProcesso")
    private RelatorioDeProcesso relatorioDeProcesso;

    public Arquivo() {

    }

    public Arquivo(String name, String type, byte[] documentoData) {
        this.name = name;
        this.type = type;
        this.documentoData = documentoData;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public byte[] getDocumentoData() {
        return documentoData;
    }

    public void setDocumentoData(byte[] documentoData) {
        this.documentoData = documentoData;
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

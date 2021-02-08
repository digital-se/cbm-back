package com.bombeiros.siteinterno.message;

import java.util.List;

public class RelatorioProcessoResponseFile {
    
    private Long id_relatorioDeProcesso;
    private int num_relatorio;
    private List<ResponseFile> documentos;

    

    public RelatorioProcessoResponseFile(Long id_relatorioDeProcesso, int num_relatorio) {
        this.id_relatorioDeProcesso = id_relatorioDeProcesso;
        this.num_relatorio = num_relatorio;
    }

    public Long getId_relatorioDeProcesso() {
        return id_relatorioDeProcesso;
    }

    public void setId_relatorioDeProcesso(Long id_relatorioDeProcesso) {
        this.id_relatorioDeProcesso = id_relatorioDeProcesso;
    }

    public int getNum_relatorio() {
        return num_relatorio;
    }

    public void setNum_relatorio(int num_relatorio) {
        this.num_relatorio = num_relatorio;
    }

    public List<ResponseFile> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ResponseFile> documentos) {
        this.documentos = documentos;
    }

    public RelatorioProcessoResponseFile(Long id_relatorioDeProcesso, int num_relatorio, List<ResponseFile> documentos) {
        this.id_relatorioDeProcesso = id_relatorioDeProcesso;
        this.num_relatorio = num_relatorio;
        this.documentos = documentos;
    }

    
    
}

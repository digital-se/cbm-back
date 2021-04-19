package com.bombeiros.siteinterno.message;

import java.util.List;

public class RelatorioProcessoResponseFile {
    
    private Long idRelatorioDeProcesso;
    private int numRelatorio;
    private List<DocumentoResponseFile> documentos;

    

    public RelatorioProcessoResponseFile(Long idRelatorioDeProcesso, int numRelatorio) {
        this.idRelatorioDeProcesso = idRelatorioDeProcesso;
        this.numRelatorio = numRelatorio;
    }

    public Long getIdRelatorioDeProcesso() {
        return idRelatorioDeProcesso;
    }

    public void setIdRelatorioDeProcesso(Long idRelatorioDeProcesso) {
        this.idRelatorioDeProcesso = idRelatorioDeProcesso;
    }

    public int getNumRelatorio() {
        return numRelatorio;
    }

    public void setNumRelatorio(int numRelatorio) {
        this.numRelatorio = numRelatorio;
    }

    public List<DocumentoResponseFile> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoResponseFile> documentos) {
        this.documentos = documentos;
    }

    public RelatorioProcessoResponseFile(Long idRelatorioDeProcesso, int numRelatorio, List<DocumentoResponseFile> documentos) {
        this.idRelatorioDeProcesso = idRelatorioDeProcesso;
        this.numRelatorio = numRelatorio;
        this.documentos = documentos;
    }

    
    
}

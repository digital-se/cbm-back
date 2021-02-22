package com.bombeiros.siteinterno.DTO;

import java.util.List;

import com.bombeiros.siteinterno.models.Documento;

public class RelatorioDeProcessoDTO {
    
    private long idRelatorioDeProcesso;
	
	private int numRelatorio;
	
	private List<Documento> documentos;

    public RelatorioDeProcessoDTO(long idRelatorioDeProcesso, int numRelatorio, List<Documento> documentos) {
        this.idRelatorioDeProcesso = idRelatorioDeProcesso;
        this.numRelatorio = numRelatorio;
        this.documentos = documentos;
    }

    public long getId() {
        return idRelatorioDeProcesso;
    }

    public void setId(long idRelatorioDeProcesso) {
        this.idRelatorioDeProcesso = idRelatorioDeProcesso;
    }

    public int getNum() {
        return numRelatorio;
    }

    public void setNum(int numRelatorio) {
        this.numRelatorio = numRelatorio;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    

}

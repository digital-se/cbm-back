package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class RelatorioProcessoDTO {

    private Long idRelatorioDeProcesso;
    private int numRelatorio;
    private List<ArquivoDTO> documentos;

    public RelatorioProcessoDTO(Long idRelatorioDeProcesso, int numRelatorio,
            List<ArquivoDTO> documentos) {
        this.idRelatorioDeProcesso = idRelatorioDeProcesso;
        this.numRelatorio = numRelatorio;
        this.documentos = documentos;
    }

    public RelatorioProcessoDTO(Long idRelatorioDeProcesso, int numRelatorio) {
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

    public List<ArquivoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ArquivoDTO> documentos) {
        this.documentos = documentos;
    }

}

package com.bombeiros.siteinterno.DTO;

import java.util.List;

public class RegistroAntigoDTO {

    private Long idRegistroAntigo;
    private String nome;
    private List<ArquivoDTO> documentos;

    public RegistroAntigoDTO(Long idRegistroAntigo, String nome, List<ArquivoDTO> documentos) {
        this.idRegistroAntigo = idRegistroAntigo;
        this.nome = nome;
        this.documentos = documentos;
    }

    public RegistroAntigoDTO(Long idRegistroAntigo, String nome) {
        this.idRegistroAntigo = idRegistroAntigo;
        this.nome = nome;
    }

    public Long getIdRegistroAntigo() {
        return idRegistroAntigo;
    }

    public void setIdRegistroAntigo(Long idRegistroAntigo) {
        this.idRegistroAntigo = idRegistroAntigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ArquivoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ArquivoDTO> documentos) {
        this.documentos = documentos;
    }

}

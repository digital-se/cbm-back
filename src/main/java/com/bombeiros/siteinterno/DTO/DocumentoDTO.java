package com.bombeiros.siteinterno.DTO;

import java.util.List;

<<<<<<< HEAD:src/main/java/com/bombeiros/siteinterno/DTO/DocumentDTO.java
public class DocumentDTO {
=======
public class DocumentoDTO {
>>>>>>> 0df2a9892884b6675974494fd429e2abf7822aa7:src/main/java/com/bombeiros/siteinterno/DTO/DocumentoDTO.java

    private long id;
    private String name;
    private int num;
<<<<<<< HEAD:src/main/java/com/bombeiros/siteinterno/DTO/DocumentDTO.java
    private List<ArquivoDTO> arquivos;

    public DocumentDTO(long id, String name, int num, List<ArquivoDTO> arquivos) {
=======
    private List<ArquivoDTO> documentos;

    public DocumentoDTO(long id, String name, int num, List<ArquivoDTO> documentos) {
>>>>>>> 0df2a9892884b6675974494fd429e2abf7822aa7:src/main/java/com/bombeiros/siteinterno/DTO/DocumentoDTO.java
        this.id = id;
        this.name = name;
        this.num = num;
        this.arquivos = arquivos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ArquivoDTO> getDocumentos() {
<<<<<<< HEAD:src/main/java/com/bombeiros/siteinterno/DTO/DocumentDTO.java
        return arquivos;
    }

    public void setDocumentos(List<ArquivoDTO> arquivos) {
        this.arquivos = arquivos;
=======
        return documentos;
    }

    public void setDocumentos(List<ArquivoDTO> documentos) {
        this.documentos = documentos;
>>>>>>> 0df2a9892884b6675974494fd429e2abf7822aa7:src/main/java/com/bombeiros/siteinterno/DTO/DocumentoDTO.java
    }

}
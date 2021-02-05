package com.bombeiros.siteinterno.DTO;

import java.util.List;

import com.bombeiros.siteinterno.models.Imagem;

public class RelatorioDeProcessoDTO {
    
    private long id_relatorioDeProcesso;
	
	private int num_relatorio;
	
	private List<Imagem> imagens;

    public RelatorioDeProcessoDTO(long id_relatorioDeProcesso, int num_relatorio, List<Imagem> imagens) {
        this.id_relatorioDeProcesso = id_relatorioDeProcesso;
        this.num_relatorio = num_relatorio;
        this.imagens = imagens;
    }

    public long getId_relatorioDeProcesso() {
        return id_relatorioDeProcesso;
    }

    public void setId_relatorioDeProcesso(long id_relatorioDeProcesso) {
        this.id_relatorioDeProcesso = id_relatorioDeProcesso;
    }

    public int getNum_relatorio() {
        return num_relatorio;
    }

    public void setNum_relatorio(int num_relatorio) {
        this.num_relatorio = num_relatorio;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    

}

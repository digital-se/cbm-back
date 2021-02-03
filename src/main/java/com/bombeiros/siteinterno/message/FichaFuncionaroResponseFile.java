package com.bombeiros.siteinterno.message;

import java.sql.Date;
import java.util.List;

public class FichaFuncionaroResponseFile {
    
    private long id_fichaFuncionario;
	
	private String nome;
	
	private int num_ficha;
	
	private Date data_inclusao;
	
    private Date data_exclusao;
    
    private List<ResponseFile> imagens;

    public FichaFuncionaroResponseFile(long id_fichaFuncionario, String nome, int num_ficha, Date data_inclusao,
            Date data_exclusao, List<ResponseFile> imagens) {
        this.id_fichaFuncionario = id_fichaFuncionario;
        this.nome = nome;
        this.num_ficha = num_ficha;
        this.data_inclusao = data_inclusao;
        this.data_exclusao = data_exclusao;
        this.imagens = imagens;
    }

    public FichaFuncionaroResponseFile(long id_fichaFuncionario, String nome, int num_ficha, Date data_inclusao,
            Date data_exclusao) {
        this.id_fichaFuncionario = id_fichaFuncionario;
        this.nome = nome;
        this.num_ficha = num_ficha;
        this.data_inclusao = data_inclusao;
        this.data_exclusao = data_exclusao;
    }

    public long getId_fichaFuncionario() {
        return id_fichaFuncionario;
    }

    public void setId_fichaFuncionario(long id_fichaFuncionario) {
        this.id_fichaFuncionario = id_fichaFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum_ficha() {
        return num_ficha;
    }

    public void setNum_ficha(int num_ficha) {
        this.num_ficha = num_ficha;
    }

    public Date getData_inclusao() {
        return data_inclusao;
    }

    public void setData_inclusao(Date data_inclusao) {
        this.data_inclusao = data_inclusao;
    }

    public Date getData_exclusao() {
        return data_exclusao;
    }

    public void setData_exclusao(Date data_exclusao) {
        this.data_exclusao = data_exclusao;
    }

    public List<ResponseFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<ResponseFile> imagens) {
        this.imagens = imagens;
    }

    
}

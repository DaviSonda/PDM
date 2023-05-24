package com.zampieri.consulta_clima;

import java.util.ArrayList;
import java.util.List;

public class CidadeTempo {
	private String nome; //Nome da Cidade
	private String uf; // UF da Cidade
	private String atualizacao; //Data da Atualizacao
	private List<Previsao> previsoes; //Previsoes para os proximos 4 dias
	public CidadeTempo() {
		super();
		this.nome = "";
		this.uf = "";
		this.atualizacao = "";
		this.previsoes = new ArrayList<Previsao>();
	}

	public CidadeTempo(String nome, String uf, String atualizacao,
                       List<Previsao> previsoes) {
		super();
		this.nome = nome;
		this.uf = uf;
		this.atualizacao = atualizacao;
		this.previsoes = previsoes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(String atualizacao) {
		this.atualizacao = atualizacao;
	}
	public List<Previsao> getPrevisoes() {
		return previsoes;
	}
	public void setPrevisoes(List<Previsao> previsoes) {
		this.previsoes = previsoes;
	}

	public void inserePrevisao(Previsao previsao){
		this.previsoes.add(previsao);
	}
	
	@Override
	public String toString() {
		Previsao p;
		String resultado = "CidadeTempo [nome=" + nome + ", uf=" + uf + ", atualizacao=" + atualizacao + ", ";
		
		for(int i=0;i<previsoes.size();i++){
			p = previsoes.get(i);
			resultado = resultado + p.toString() + ", "; 
		}

		return  resultado; // "nome=" + nome + ", uf=" + uf +", atualizacao=" + atualizacao;
	}
	
}

package com.zampieri.estadosbrasil;

public class Estados {
	private String estado;
	private String abreviacao;
	private String capital;
	private float area;    
	private int bandeira;

	public Estados(String estado, String abreviacao, String capital, float area, int bandeira) {
		this.estado = estado;
		this.abreviacao = abreviacao;
		this.capital = capital;
		this.area = area;
		this.bandeira = bandeira;
	}

	public String getEstado() {
		return estado;    
	}    
	public void setEstado(String estado) {
		this.estado = estado;    
	}    
	public String getAbreviacao() {
		return abreviacao;    
	}    
	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;    
	}    
	public String getCapital() {
		return capital;    
	}    
	public void setCapital(String capital) {
		this.capital = capital;    
	}    
	public float getArea() {        
		return area;    
	}    
	public void setArea(float area) {        
		this.area = area;    
	}    
	public int getBandeira() {        
		return bandeira;    
	}    
	public void setBanner(int bandeira) {        
		this.bandeira = bandeira;    
	}
}


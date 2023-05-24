package com.zampieri.consulta_clima;

public class Previsao {
	private String data;
	private String tempo;
	private String maxima;
	private String minima;
	private String iuv;
	public Previsao() {
		super();
		this.data = "";
		this.tempo = "";
		this.maxima = "";
		this.minima = "";
		this.iuv = "";
	}
	
	public Previsao(String data, String tempo, String maxima, String minima,
                    String iuv) {
		super();
		this.data = data;
		this.tempo = tempo;
		this.maxima = maxima;
		this.minima = minima;
		this.iuv = iuv;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getMaxima() {
		return maxima;
	}
	public void setMaxima(String maxima) {
		this.maxima = maxima;
	}
	public String getMinima() {
		return minima;
	}
	public void setMinima(String minima) {
		this.minima = minima;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	@Override
	public String toString() {
		return "Previsao [data=" + data + ", tempo=" + tempo + ", maxima="
				+ maxima + ", minima=" + minima + ", iuv=" + iuv + "]";
	}
}

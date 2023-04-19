package com.davisonego.pesquisa;

public class Pessoa {
    private Integer idade;
    private String sexo;
    private String corOlhos;
    private String corCabelo;
    public Pessoa (Integer idade, String sexo, String corOlhos, String corCabelo) {
        this.idade = idade;
        this.sexo = sexo;
        this.corOlhos = corOlhos;
        this.corCabelo = corCabelo;
    }
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo (String sexo) {
        this.sexo = sexo;
    }
    public String getCorOlhos() {
        return corOlhos;
    }
    public void setCorOlhos (String corOlhos) {
        this.corOlhos = corOlhos;
    }
    public String getCorCabelo() {
        return corCabelo;
    }
    public void setCorCabelo (String corCabelo) {
        this.corCabelo = corCabelo;
    }
    @Override
    public String toString() {
        return "Idade:" + idade + "\nsexo: " + sexo+ "\nOlhos: " + corOlhos+ "\nCabelo: " + corCabelo;
    }
}

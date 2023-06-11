package com.davisonego.cotacoes;

public class Pais {
    String Bandeira;
    String Nome;
    String Cotacao;

    public Pais(String bandeira, String nome, String cotacao) {
        Bandeira = bandeira;
        Nome = nome;
        Cotacao = cotacao;
    }

    public String getBandeira() {
        return Bandeira;
    }

    public void setBandeira(String bandeira) {
        Bandeira = bandeira;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCotacao() {
        return Cotacao;
    }

    public void setCotacao(String cotacao) {
        Cotacao = cotacao;
    }
}

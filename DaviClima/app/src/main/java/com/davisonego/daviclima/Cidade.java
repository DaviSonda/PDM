package com.davisonego.daviclima;

public class Cidade {
    String Nome;
    String UF;
    String Codigo;

    public Cidade(String nome, String uf, String cod) {
        Nome = nome;
        UF = uf;
        Codigo = cod;
    }

    public Cidade() {

    }

    public String getNome() {
        return Nome;
    }

    public String getUF() {
        return UF;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "Nome='" + Nome + '\'' +
                ", UF='" + UF + '\'' +
                ", Codigo='" + Codigo + '\'' +
                '}';
    }
}

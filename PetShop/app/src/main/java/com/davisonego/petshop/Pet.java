package com.davisonego.petshop;

public class Pet {
    private String Titulo;
    private String Descricao;
    private String Img;
    private String Tipo_Pub;
    private String Metodo_Contato;

    public Pet(String tit, String desc, String img, String tipo, String contato) {
        this.Titulo = tit;
        this.Descricao = desc;
        this.Tipo_Pub = tipo;
        this.Img = img;
        this.Metodo_Contato = contato;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getDescricao() {
        return Descricao;
    }
    public String getContato() {
        return Metodo_Contato;
    }
    public String getTipo() {
        return Tipo_Pub;
    }
    public String getImg() {
        return Img;
    }

    @Override
    public String toString() {
        return "Titulo:" + Titulo + "\nDescricao: " + Descricao + "\nContato: " + Metodo_Contato;
    }
}

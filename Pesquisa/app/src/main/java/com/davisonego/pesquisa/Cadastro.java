package com.davisonego.pesquisa;

import java.util.ArrayList;

public final class Cadastro {
    private static ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
    public static ArrayList<Pessoa> getLista() {
        return lista;
    }
    public static void addPessoa (Pessoa p){
        lista.add(p);
    }
    public static Pessoa getContato (int pos) {
        return lista.get(pos);
    }
}

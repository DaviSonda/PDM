package com.davisonego.contatos;

import java.util.ArrayList;

public final class Cadastro {
    private static ArrayList<Contato> lista = new ArrayList<Contato>();
    public static ArrayList<Contato> getLista() {
        return lista;
    }
    public static void addContato (Contato contato){
        lista.add(contato);
    }
    public static Contato getContato (int pos) {
        return lista.get(pos);
    }
}

package com.davisonego.davisonegoa1;

import java.util.ArrayList;

public class Cidade {
    Integer Codigo;
    String Nome;
    Integer QuantidadeVeiculos;
    Integer QuantidadeAcidentes;

    public Cidade() {
    }

    public Cidade(Integer codigo, String nome, Integer quantidadeVeiculos, Integer quantidadeAcidentes) {
        Codigo = codigo;
        Nome = nome;
        QuantidadeVeiculos = quantidadeVeiculos;
        QuantidadeAcidentes = quantidadeAcidentes;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer codigo) {
        Codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Integer getQuantidadeVeiculos() {
        return QuantidadeVeiculos;
    }

    public void setQuantidadeVeiculos(Integer quantidadeVeiculos) {
        QuantidadeVeiculos = quantidadeVeiculos;
    }

    public Integer getQuantidadeAcidentes() {
        return QuantidadeAcidentes;
    }

    public void setQuantidadeAcidentes(Integer quantidadeAcidentes) {
        QuantidadeAcidentes = quantidadeAcidentes;
    }

    public Cidade getMaiorAcidente(ArrayList<Cidade> cidades) {
        //Cidade com maior numero de acidentes
        Cidade cidade = null;
        for (int i = 0; i < cidades.size(); i++) {
            Cidade item = cidades.get(i);
            if (cidade == null) {
                cidade = item;
            } else {
                if (cidade.QuantidadeAcidentes < item.QuantidadeAcidentes) {
                    cidade = item;
                }
            }
        }
        return cidade;
    }

    public Cidade getMenorAcidente(ArrayList<Cidade> cidades) {
        //Cidade com maior numero de acidentes
        Cidade cidade = null;
        for (int i = 0; i < cidades.size(); i++) {
            Cidade item = cidades.get(i);
            if (cidade == null) {
                cidade = item;
            } else {
                if (cidade.QuantidadeAcidentes > item.QuantidadeAcidentes) {
                    cidade = item;
                }
            }
        }
        return cidade;
    }

    public Integer getMediaVeiculos(ArrayList<Cidade> cidades) {
        Integer media = 0;
        for (int i = 0; i < cidades.size(); i++) {
            media = media + cidades.get(i).QuantidadeVeiculos;
        }
        return media / cidades.size();
    }

    public String getMediaAcidentesCidadePequena(ArrayList<Cidade> cidades) {
        String resultado = "Nao ha nenhuma cidade com menos de 2000 veiculos";
        Integer media = 0;
        Integer contador = 0;
        for (int i = 0; i < cidades.size(); i++) {
            Cidade item = cidades.get(i);
            if (item.getQuantidadeVeiculos() <= 2000) {
                contador = contador + 1;
                media = media + item.QuantidadeAcidentes;
            }
        }
        if (contador > 0) {
            resultado = "A media de acidentes em cidades com menos de 2000 veiculos e: " + media / contador;
        }
        return resultado;
    }
}

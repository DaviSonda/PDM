package com.davisonego.petshop;

public class PetDisplay {
    String img;
    String Tit;
    String Des;

    String Cont;
    String Id;

    public PetDisplay(String image, String tit, String des, String cont, String id) {
        img = image;
        Tit = tit;
        Des = des;
        Cont = cont;
        Id = id;
    }

    public String getCont() {
        return Cont;
    }

    public String getImg() {
        return img;
    }

    public String getTit() {
        return Tit;
    }
    public String getId() {
        return Id;
    }

    public void setTit(String tit) {
        Tit = tit;
    }

    public String getDes() {
        return Des;
    }
}

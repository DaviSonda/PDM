package com.davisonego.petshop;

public class PetDisplay {
    String img;
    String Tit;
    String Des;

    String Cont;

    public PetDisplay(String image, String tit, String des, String cont) {
        img = image;
        Tit = tit;
        Des = des;
        Cont = cont;
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

    public void setTit(String tit) {
        Tit = tit;
    }

    public String getDes() {
        return Des;
    }
}

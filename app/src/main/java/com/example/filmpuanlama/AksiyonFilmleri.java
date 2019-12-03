package com.example.filmpuanlama;

public class AksiyonFilmleri implements IFilmler {

    private static AksiyonFilmleri aksiyonFilmleri = new AksiyonFilmleri();

    private AksiyonFilmleri() {    }

    public static AksiyonFilmleri nesne() {

        return aksiyonFilmleri;
    }


    @Override
    public String filmismi(String filmismi) {
        return  filmismi;
    }

    @Override
    public String filmresmi(String filmresmi) {
        return filmresmi;
    }
}



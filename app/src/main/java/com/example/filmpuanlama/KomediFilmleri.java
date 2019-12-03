package com.example.filmpuanlama;

public class KomediFilmleri implements IFilmler {

    private static KomediFilmleri komediFilmleri = new KomediFilmleri();

    private KomediFilmleri() {    }

    public static KomediFilmleri nesne() {

        return komediFilmleri;
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


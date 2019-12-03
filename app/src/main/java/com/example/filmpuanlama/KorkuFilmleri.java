package com.example.filmpuanlama;

public class KorkuFilmleri implements IFilmler {

    private static KorkuFilmleri korkuFilmleri = new KorkuFilmleri();

    private KorkuFilmleri() {    }

    public static KorkuFilmleri nesne() {

        return korkuFilmleri;
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

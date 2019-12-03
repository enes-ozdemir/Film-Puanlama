package com.example.filmpuanlama;

public class FilmMaker {

    private IFilmler KorkuFilmleri;
    private IFilmler KomediFilmleri;
    private IFilmler AksiyonFilmleri;


    private static FilmMaker filmMaker = new FilmMaker();

    private FilmMaker() {
        KomediFilmleri = com.example.filmpuanlama.KomediFilmleri.nesne();
        KorkuFilmleri = com.example.filmpuanlama.KorkuFilmleri.nesne();
        AksiyonFilmleri= com.example.filmpuanlama.AksiyonFilmleri.nesne();
    }

    public static FilmMaker nesne() {

        return filmMaker;
    }



    public void KorkuFilmiOlustur(String filmismi,String filmresmi) {

        KorkuFilmleri.filmismi(filmismi);
        KorkuFilmleri.filmresmi(filmresmi);


    }
    public void KomediFilmiOlustur(String filmismi,String filmresmi) {

        KomediFilmleri.filmismi(filmismi);
        KomediFilmleri.filmresmi(filmresmi);


    }
    public void AksiyonFilmiOlustur(String filmismi,String filmresmi) {

        AksiyonFilmleri.filmismi(filmismi);
        AksiyonFilmleri.filmresmi(filmresmi);

    }


}





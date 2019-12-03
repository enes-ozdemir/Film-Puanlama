package com.example.filmpuanlama;

public class ProfileMaker {

    private IProfil Profil;

    private static ProfileMaker nesne =new ProfileMaker();

    private ProfileMaker() {}

    public static ProfileMaker Nesne() {


        return nesne;
    }



    /* public void ProfilOlustur(String a, String b, int c, String d, String e, String f, String g) {
        Profil= com.example.filmpuanlama.Profil.Nesne();

            Profil.isim(a);
            Profil.Soyisim(b);
            Profil.Yas(c);
            Profil.Email();
            Profil.Resim(e);
            Profil.Sifre(f);
            Profil.Key(g);



    } */

}

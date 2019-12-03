package com.example.filmpuanlama;

public class Profil  {

    private String kisi_key;
    private String mail;
    private String name;
    private String surname;
    private String password;
    private int age;


    public Profil(String kisi_key) {
        this.kisi_key = kisi_key;
    }

    public String getKisi_key() {
        return kisi_key;
    }

    public void setKisi_key(String kisi_key) {
        this.kisi_key = kisi_key;
    }

    private static Profil nesne =new Profil();

    private Profil() {}

    public static Profil Nesne() {


        return nesne;
    }

    public static Profil getNesne() {
        return nesne;
    }

    public static void setNesne(Profil nesne) {
        Profil.nesne = nesne;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
/*   public Profil ProfilOlustur(String a, String b, int c, String d, String e, String f, String g) {

        Nesne().isim(a);
        Nesne().Soyisim(b);
        Nesne().Yas(c);
        Nesne().Email();
        Nesne().Resim(e);
        Nesne().Sifre(f);
        Nesne().Key(g);

        return nesne;
    } */
}

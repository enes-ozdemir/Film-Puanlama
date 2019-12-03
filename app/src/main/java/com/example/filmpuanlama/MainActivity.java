package com.example.filmpuanlama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button film_siralama = findViewById(R.id.film_siralama);
        Button kayit_ol = findViewById(R.id.kayit_ol);
        Button film_ekle = findViewById(R.id.film_ekle);
        Button giris_yap = findViewById(R.id.giris_yap);
        Button film_puanla = findViewById(R.id.film_puanla);
        Button btn_cikis = findViewById(R.id.btn_cikis);

        film_ekle.setVisibility(View.INVISIBLE);
        film_puanla.setVisibility(View.INVISIBLE);

        final Intent kayit = new Intent(this, Kayit_Ol.class);
        final Intent giris = new Intent(this,Giris_Yap.class);
        final Intent anasayfa= new Intent(this,MainActivity.class);
        final Intent filmekle=new Intent(this,Film_Ekle.class);

        boolean girise = PreferenceManager.Instance(getApplicationContext()).GirisKontrol();
            String ali=String.valueOf(girise);
        Log.e("hata",ali);

        if(girise) {
            film_ekle.setVisibility(View.VISIBLE);
            film_puanla.setVisibility(View.VISIBLE);
            giris_yap.setVisibility(View.INVISIBLE);
            kayit_ol.setVisibility(View.INVISIBLE);
        }
        kayit_ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(kayit);
            }
        });

        giris_yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(giris);
            }
        });
        btn_cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.Instance(getApplicationContext()).Girisyok();
                startActivity(anasayfa);

            }
        });
        film_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(filmekle);
            }
        });
        film_puanla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opemImagesActivity();
            }
        });
    }

    private void opemImagesActivity() {
        Intent intent=new Intent(this,FilmListesi.class);
        startActivity(intent);

    }
}

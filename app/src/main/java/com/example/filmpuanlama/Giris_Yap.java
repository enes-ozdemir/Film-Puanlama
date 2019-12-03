package com.example.filmpuanlama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Giris_Yap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);


        final Button btn_giris = findViewById(R.id.btn_giris);
        final Button btn_kayit = findViewById(R.id.btn_kayit);
        final EditText et_email= findViewById(R.id.et_mail);
        final EditText et_sifre= findViewById(R.id.et_sifre);
        final Intent kayit = new Intent(this, Kayit_Ol.class);
        final Intent anasayfa=new Intent(this,MainActivity.class);

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(kayit);
            }
        });

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Profil");
                String sifre;

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Girilen_mail= et_email.getText().toString();
                        String Girilen_sifre= et_sifre.getText().toString();

                        for(DataSnapshot d:dataSnapshot.getChildren()) {
                            Profil pro=d.getValue(Profil.class);
                            String mail=pro.getMail();
                            String sifre=pro.getPassword();

                            System.out.println(mail);
                            System.out.println(sifre);
                            String key =d.getKey();

                            if(Girilen_mail.equals(mail) & Girilen_sifre.equals(sifre)) {
                                //Tekrar anasayfaya atabilirsin daha sonra giriş ve kayıt olu kaldırabilirsin.

                                Toast.makeText(Giris_Yap.this, "Başarı ile giriş yaptınız", Toast.LENGTH_SHORT).show();

                                PreferenceManager.Instance(getApplicationContext()).Girisvar();

                                startActivity(anasayfa);
                                break;


                            }
                            else {
                                Toast.makeText(Giris_Yap.this, "Bilgileri doğru girdiğinizden emin olunuz", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}

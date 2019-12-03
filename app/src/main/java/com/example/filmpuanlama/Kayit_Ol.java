package com.example.filmpuanlama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Kayit_Ol extends AppCompatActivity {
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView mImageView;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);


        Button btn_yukle = findViewById(R.id.btn_yukle);
        Button btn_kayitol = findViewById(R.id.btn_kayitol);
        final EditText et_email = findViewById(R.id.et_email);
        final EditText et_isim = findViewById(R.id.et_isim);
        final EditText et_soyisim = findViewById(R.id.et_soyisim);
        final EditText et_sifre = findViewById(R.id.et_sifre);
        final EditText et_yas = findViewById(R.id.et_yas);
        final Profil a = Profil.Nesne(); //Singleton ile nesne olusturuldu.
        mImageView = findViewById(R.id.img_kisi);
        mProgressBar = findViewById(R.id.progressBar);


        btn_yukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStorageRef = FirebaseStorage.getInstance().getReference("uploads/profiles");
                mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

                Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

                openFileChooser();


            }


        });


        btn_kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (false) {
                    //if((et_email.getText().toString().length()<=10|et_isim.getText().toString().length()<2||et_sifre.getText().toString().length()<=1||et_soyisim.getText().toString().length()<=2||et_yas.getText().length()<=1)) {
                    Toast.makeText(Kayit_Ol.this, "Bilgileri doğru ve eksiksiz girdiğinize emin olunuz", Toast.LENGTH_SHORT).show();
                } else {
                    mail = et_email.getText().toString();
                    String isim = et_isim.getText().toString();
                    String soyisim = et_soyisim.getText().toString();
                    int yas = Integer.valueOf(et_yas.getText().toString());
                    String resim_yolu = et_email.getText().toString();
                    String sifre = et_sifre.getText().toString();
                    int kisi_key = 100;
                    // kisi_key++;

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Profil");


                    Map<String, Object> bilgiler = new HashMap<>();

                    bilgiler.put("name", isim);
                    bilgiler.put("surname", soyisim);
                    bilgiler.put("mail", mail);
                    bilgiler.put("age", yas);
                    bilgiler.put("password", sifre); //Şifreyi şifrele sonradan
                    bilgiler.put("key", kisi_key);

                    myRef.push().setValue(bilgiler);
                    uploadFile();
                    Toast.makeText(getApplicationContext(), "Başarı ile kayıt oldunuz", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            mImageView.setImageURI(mImageUri);
        }
    }

    private String getFileExtension(Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getMimeTypeFromExtension(cR.getType(uri));
    }


    private void uploadFile() { //5:43 DESİN
        if (mImageUri != null) {
            final StorageReference fileReferance = mStorageRef.child(mail+"."+System.currentTimeMillis());

            fileReferance.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);

                        }
                    }, 500);

                    Toast.makeText(Kayit_Ol.this, "Yükleme Başarılı", Toast.LENGTH_LONG).show();
                    Resim_Yukle upload = new Resim_Yukle("Resim" + System.currentTimeMillis(), taskSnapshot.getUploadSessionUri().toString());

                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Kayit_Ol.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) progress);
                }
            });

        } else {
            Toast.makeText(this, "Herhangi bir resim seçilmedi", Toast.LENGTH_SHORT).show();
        }


    }
}

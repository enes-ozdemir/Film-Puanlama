package com.example.filmpuanlama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Film_Ekle extends AppCompatActivity {
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView mImageView;
    private DatabaseReference mDatabaseRef;
String filmadi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_ekle);

         mImageView = findViewById(R.id.resim);
        final EditText txt_film = findViewById(R.id.txt_film);
        final Button btn_ekle = findViewById(R.id.btn_ekle);
        final Button btn_yukle = findViewById(R.id.btn_yukle);



        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            FilmMaker.nesne().AksiyonFilmiOlustur(txt_film.getText().toString(),filmadi);
                filmadi=txt_film.getText().toString();

                uploadFile();


            }
        });
        btn_yukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStorageRef = FirebaseStorage.getInstance().getReference("uploads/movies");
                openFileChooser();

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


    private void uploadFile() {
        if (mImageUri != null) {

            final StorageReference fileReferance = mStorageRef.child(filmadi);

            fileReferance.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 500);

                    Toast.makeText(Film_Ekle.this, "Yükleme Başarılı", Toast.LENGTH_LONG).show();
                    Resim_Yukle upload = new Resim_Yukle("Resim" + System.currentTimeMillis(), taskSnapshot.getUploadSessionUri().toString());

                  /*  String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);*/

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Film_Ekle.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(this, "Herhangi bir resim seçilmedi", Toast.LENGTH_SHORT).show();
        }


    }



}

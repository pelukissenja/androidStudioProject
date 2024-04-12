package com.talentiva.absensi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.cloudinary.android.MediaManager;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText namaInstansi, emailInstansi, namaKepsek, nipKepsek, kontakInstansi, logoInstansi, alamatInstansi;
    private Button btnSimpan;
    private DatabaseReference db;

    private ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri imageUri = result.getData().getData();
                        uploadImageToCloudinary(imageUri);
                    }

                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        namaInstansi = findViewById(R.id.etFullName);
        emailInstansi = findViewById(R.id.etEmail);
        namaKepsek = findViewById(R.id.etKepsek);
        nipKepsek = findViewById(R.id.etNipKepsek);
        kontakInstansi = findViewById(R.id.etKontakInstansi);
        logoInstansi = findViewById(R.id.etLogo);
        alamatInstansi = findViewById(R.id.etAlamat);
        btnSimpan = findViewById(R.id.regBtn);
        db = FirebaseDatabase.getInstance().getReference().child("Instansi");

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });
        logoInstansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                pickImageLauncher.launch(Intent.createChooser(intent, "Pilih Gambar"));
            }
        });

    }
        private void uploadImageToCloudinary(Uri imageUri){
            String imagePath = getPathFromUri(imageUri);
            if(imagePath != null){
                MediaManager.get().upload(imagePath)
                        .unsigned("@t4l3nt!v4_preset")
                        .callback(new UploadCallback() {
                            @Override
                            public void onStart(String requestId) {
                            }

                            @Override
                            public void onProgress(String requestId, long bytes, long totalBytes) {

                            }

                            @Override
                            public void onSuccess(String requestId, Map resultData) {
                                String imageUrl = (String) resultData.get("url");
                            }

                            @Override
                            public void onError(String requestId, ErrorInfo error) {
                                Toast.makeText(RegisterActivity.this, "Gagal mengupload gambar", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onReschedule(String requestId, ErrorInfo error) {
                                Toast.makeText(RegisterActivity.this, "Gagal mengupload gambar", Toast.LENGTH_SHORT).show();

                            }
                        }).dispatch();
            }
        }
        private String getPathFromUri(Uri uri){
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
            if(cursor != null){
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imagePath = cursor.getString(columnIndex);
                cursor.close();
                return imagePath;
            }
            return null;
        }
        private void simpanData(){
            String sekolah = namaInstansi.getText().toString().trim();
            String emailsekolah = emailInstansi.getText().toString().trim();
            String kepsekName = namaKepsek.getText().toString().trim();
            String kepsekNip = nipKepsek.getText().toString().trim();
            String kontak = kontakInstansi.getText().toString().trim();
            String logoins = logoInstansi.getText().toString().trim();
            String almtInstans = alamatInstansi.getText().toString().trim();

            if(!TextUtils.isEmpty(sekolah)){
                String id = db.push().getKey();
                Instansi instansi = new Instansi(id, sekolah, emailsekolah, kepsekName, kepsekNip, kontak, logoins, almtInstans);

                assert  id != null;
                db.child(id).setValue(instansi);

                Toast.makeText(this, "Data Berhasil disimpan~", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Data Gagal disimpan~", Toast.LENGTH_SHORT).show();
            }
        }


}
package com.example.ezmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test_fac extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] courses = {"Mobile Application Development", "Data structures",
            "Software Engineering", "Artificial Intelligence",
            "Probability And Statistics", "Image Processing", "Python Programming", "Java Programming"};
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    Spinner spin;
    Button test_btn;
    TextView dateFormat;
    int year;
    int month;
    int day;
    Uri pdfUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fac);
        spin = findViewById(R.id.spinner_test_fac);
        test_btn = findViewById(R.id.fac_test_btn);
        dateFormat = findViewById(R.id.dateFormatID2);
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        spin.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        test_btn.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(test_fac.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                selectPDF();
                if (pdfUri != null) {
                    uploadFile(pdfUri);
                }
            } else {
                ActivityCompat.requestPermissions(test_fac.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });


        Calendar calendar = Calendar.getInstance();
        dateFormat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(test_fac.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateFormat.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void uploadFile(Uri pdfUri) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String filename=System.currentTimeMillis()+".pdf";
        final String filename1 = System.currentTimeMillis()+"";
        StorageReference storageReference=storage.getReference();
        storageReference.child("Uploads").child(filename).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        DatabaseReference reference=database.getReference();
                        reference.child(filename1).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(test_fac.this, "File Successfully uploaded", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(test_fac.this, "File not Successfully uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(test_fac.this, "File not Successfully uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress= (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            selectPDF();
        }
        else
            Toast.makeText(test_fac.this, "please provide permission",Toast.LENGTH_SHORT).show();


    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
            pdfUri = data.getData();
        }
        else{
            Toast.makeText(test_fac.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
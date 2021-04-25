package com.example.ezmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_page extends AppCompatActivity {
    EditText ed1,ed2;
    Button btn_login;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        firebaseauth = FirebaseAuth.getInstance();
        ed1 = findViewById(R.id.user_txt);
        ed2 = findViewById(R.id.pass_txt);
        btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(view ->{
            toPortal();
        });
    }

    public void toPortal() {
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            ed1.setError("Username cannot be empty");
            ed1.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            ed2.setError("Username cannot be empty");
            ed2.requestFocus();
        }
        else
        {
            firebaseauth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login_page.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login_page.this,data_retrieve.class));
                    }
                }
            });
        }

    }


}
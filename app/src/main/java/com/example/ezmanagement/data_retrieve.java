package com.example.ezmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class data_retrieve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieve);
        FirebaseAuth firebaseauth;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            String username = user.getEmail().toString();
            Toast.makeText(data_retrieve.this, username, Toast.LENGTH_SHORT).show();

            String s =username.substring(0,1);
            Toast.makeText(data_retrieve.this, s, Toast.LENGTH_LONG).show();

            if(s == "s")
            {
                startActivity(new Intent(data_retrieve.this,student_page.class));
            }
            else if(s == "f")
            {
                startActivity(new Intent(data_retrieve.this,faculty_page.class));
            }


        }
    }
}
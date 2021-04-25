package com.example.ezmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login_page extends AppCompatActivity {
    EditText ed1,ed2;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void toPortal(View v) {

        btn_login = (Button) findViewById(R.id.login_btn);
        ed1 = findViewById(R.id.user_txt);
        ed2 = findViewById(R.id.pass_txt);
        if (ed1.getText().toString().equals("stu123") && ed2.getText().toString().equals("svkm")) {
            Intent l = new Intent(login_page.this, student_page.class);
            startActivity(l);
            Toast.makeText(login_page.this, "Welcome Student", Toast.LENGTH_SHORT).show();
        }

        else if (ed1.getText().toString().equals("fac123") && ed2.getText().toString().equals("svkm"))
        {
            Intent l = new Intent(login_page.this, faculty_page.class);
            startActivity(l);
            Toast.makeText(login_page.this, "Welcome Faculty", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(login_page.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
        }

    }


}
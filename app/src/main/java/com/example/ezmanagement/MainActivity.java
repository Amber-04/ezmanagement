package com.example.ezmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toLogin(View v)
    {

        b = (Button) findViewById(R.id.main_login);
        Intent i = new Intent(MainActivity.this, login_page.class);
        startActivity(i);
    }

}

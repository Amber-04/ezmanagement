package com.example.ezmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class attendance_stu extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] courses = {"Mobile Application Development", "Data structures",
            "Software Engineering", "Artificial Intelligence",
            "Probability And Statistics", "Image Processing", "Python Programming", "Image Processing", "Java Programming"};
    Spinner spin;
    Button atn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_stu);
    spin = findViewById(R.id.spinner_atn_stu);
    atn_btn = findViewById(R.id.chk_atn_btn);
    spin.setOnItemSelectedListener(this);
    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spin.setAdapter(arrayAdapter);



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
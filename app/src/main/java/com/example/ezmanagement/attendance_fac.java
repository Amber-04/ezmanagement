package com.example.ezmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class attendance_fac extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] courses = {"Mobile Application Development", "Data structures",
            "Software Engineering", "Artificial Intelligence",
            "Probability And Statistics", "Image Processing", "Python Programming", "Image Processing", "Java Programming"};
    Spinner spin;
    Button atn_btn;
    TextView dateFormat;
    int year;
    int month;
    int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_fac);
        spin = findViewById(R.id.spinner_atn_fac);
        atn_btn = findViewById(R.id.fac_chk_atn_btn);
        spin.setOnItemSelectedListener(this);
        dateFormat = findViewById(R.id.dateFormatID3);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner_item,courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);

        Calendar calendar = Calendar.getInstance();
        dateFormat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(attendance_fac.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateFormat.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
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
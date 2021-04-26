 package com.example.ezmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class lecture_fac extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] courses = {"Mobile Application Development", "Data structures",
            "Software Engineering", "Artificial Intelligence",
            "Probability And Statistics", "Image Processing", "Python Programming", "Image Processing", "Java Programming"};
    Spinner spin;
    TextView dateFormat, TimeTextview;
    private TextView pickTime;
    int year;
    int month;
    int day;
    Button atn_btn;
    Button btn_email;
    public String date;
    public String time;
    public String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_fac);
        spin = findViewById(R.id.spinner_lec_fac);
        dateFormat = findViewById(R.id.dateFormatID);
        TimeTextview = findViewById(R.id.pickTime);
        pickTime = findViewById(R.id.pickTime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(lecture_fac.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format;
                        format = new SimpleDateFormat("k:mm a");
                        time = format.format(c.getTime());
                        TimeTextview.setText(time);
                    }
                }, hours, minutes, false);
                timePickerDialog.show();
            }
        });


        Calendar calendar = Calendar.getInstance();
        dateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(lecture_fac.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        int m = calendar.get(Calendar.MONTH);
                        m++;

                        date = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(m) + "-" +
                                String.valueOf(calendar.get(Calendar.YEAR));
                        dateFormat.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        atn_btn = findViewById(R.id.lecture_btn);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        btn_email = findViewById(R.id.lecture_btn);
        btn_email.setOnClickListener(view -> {
            sendEmail();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    protected void sendEmail() {
       /* Log.i("Send email", "");

        String[] TO = {"aaki576@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Lecture hai bunty");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(lecture_fac.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }*/
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Test"+ "&body=" + "Lecture of "+item+" on :" + "\nDate : " + date + "\nTime : " + time + "&to=" + "aaki576@gmail.com,rohanpandav9@gmail.com,ambersupadhyay@gmail.com");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }
}

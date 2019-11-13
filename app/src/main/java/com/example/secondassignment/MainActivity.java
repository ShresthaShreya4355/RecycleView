package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextDob, editTextPhone, editTextEmail, editTextImage;
    RadioGroup radioGroupGender;
    Spinner spinnerCountry;
    Button buttonSubmit, buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.etName);
        editTextDob = findViewById(R.id.etBirth);
        editTextPhone = findViewById(R.id.etPhone);
        editTextEmail = findViewById(R.id.etEmail);
        editTextImage = findViewById(R.id.etImage);
        radioGroupGender = findViewById(R.id.rgGender);
        spinnerCountry = findViewById(R.id.spCountry);
        buttonSubmit = findViewById(R.id.btnSubmit);
        buttonView = findViewById(R.id.btnView);
    }
}

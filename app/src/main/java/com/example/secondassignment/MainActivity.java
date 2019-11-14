package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.secondassignment.Model.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    EditText editTextName, editTextDob, editTextPhone, editTextEmail;
    AutoCompleteTextView image;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMale, radioButtonFemale,radioButtonOther;
    Spinner spinnerCountry;
    Button buttonSubmit, buttonView;

    String[] images = {"shreya","shristi","poonam","muskan","manuel"};
    String[] country ={"--Please select country--","Nepal","China","America","India","Srilanka","Bhutan"};
    Calendar calendar = Calendar.getInstance();

    final List<User> user = new ArrayList<>();
    private String userName, userDob, userPhone, userEmail,userGender, userCountry, userImage;


//to inster date of birth date picker is used


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.etName);
        editTextDob = findViewById(R.id.etBirth);
        editTextPhone = findViewById(R.id.etPhone);
        editTextEmail = findViewById(R.id.etEmail);
        image = findViewById(R.id.etImage);
        radioGroupGender = findViewById(R.id.rgGender);
        spinnerCountry = findViewById(R.id.spCountry);
        buttonSubmit = findViewById(R.id.btnSubmit);
        buttonView = findViewById(R.id.btnView);

        editTextDob.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        radioGroupGender.setOnCheckedChangeListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,R.layout.images,images);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.countryname, country);
        image.setAdapter(arrayAdapter);
        image.setThreshold(1);
        spinnerCountry.setAdapter(adapter);
        setValue();
    }

    private void setValue(){
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userCountry = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnSubmit) {
            userName = editTextName.getText().toString();
            userEmail = editTextEmail.getText().toString();
            userDob = editTextDob.getText().toString();
            userImage = image.getText().toString();
            userPhone = editTextPhone.getText().toString();

            String uri = "@drawable/" + userImage;
            int resID = getResources().getIdentifier(uri, null, getPackageName());

            if (validate()) {
                if (v.getId() == R.id.btnSubmit) {
                    user.add(new User(userName, userDob, userEmail, userGender, userPhone, userCountry, resID));

                    Toast.makeText(this, "User has been added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(v.getId() == R.id.etBirth){

            DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myDateFormat = "dd-MM-y";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myDateFormat);
                    editTextDob.setText(simpleDateFormat.format(calendar.getTime()));

                }
            };
            new DatePickerDialog(this,myDatePicker,
                    calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        }
        if (v.getId() == R.id.btnView){
            Intent intent = new Intent(MainActivity.this,UserListActivity.class);
            intent.putExtra("allusers",(Serializable) user);
            startActivity(intent);
        }
    }



    private boolean validate() {
        if (TextUtils.isEmpty(userName)) {
            editTextName.setError("Enter Name");
            return false;

        }
        if (TextUtils.isEmpty(userEmail)) {
            editTextEmail.setError("Enter Email");
            return false;

        }

        if (TextUtils.isEmpty(userDob)) {
            editTextDob.setError("Enter DOB");
            return false;

        }
        if (TextUtils.isEmpty(userPhone)) {
            editTextPhone.setError("Enter Phone");
            return false;

        }
        if (TextUtils.isEmpty(userImage)) {
            image.setError("Enter image");
            return false;

        }
        if (TextUtils.isEmpty(userCountry)) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(!TextUtils.isDigitsOnly(userPhone)){
            editTextPhone.setError("Invalid Phone");
            return false;

        }
        if(TextUtils.isEmpty(userGender)){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            editTextEmail.setError("Invalid Email");
            return false;
        }

        return true;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.rbMale){
            userGender ="Male";
            Toast.makeText(this,"Male",Toast.LENGTH_SHORT).show();
        }

        if(checkedId == R.id.rbFemale){
            userGender ="Female";
            Toast.makeText(this,"Female",Toast.LENGTH_SHORT).show();
        }

        if(checkedId == R.id.rbOther){
            userGender ="Other";
            Toast.makeText(this,"Other",Toast.LENGTH_SHORT).show();
        }
    }
}

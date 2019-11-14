package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    ImageView image;
    TextView name,dob,email,gender,country,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        email=findViewById(R.id.email);
        gender = findViewById(R.id.gender);
        country = findViewById(R.id.country);
        phone =findViewById(R.id.phone);
        image = findViewById(R.id.imagee);

        Bundle bundle =getIntent().getExtras();
        name.setText(bundle.getString("name"));
        dob.setText(bundle.getString("dob"));
        email.setText(bundle.getString("email"));
        gender.setText(bundle.getString("gender"));
        country.setText(bundle.getString("country"));
        phone.setText(bundle.getString("phone"));
        image.setImageResource(bundle.getInt("image"));

    }
}

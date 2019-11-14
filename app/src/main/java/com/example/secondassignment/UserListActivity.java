package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.secondassignment.Model.User;

import java.util.List;

public class UserListActivity extends AppCompatActivity {


    RecyclerView userlistview;
    List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        userlistview = findViewById(R.id.userlistview);
        Intent i = getIntent();
        users = (List<User>) i.getSerializableExtra("allusers");
        UserAdapater adapter = new UserAdapater(this,users);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userlistview.setLayoutManager(layoutManager);
        userlistview.setAdapter(adapter);


    }

}

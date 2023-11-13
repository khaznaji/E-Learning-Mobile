/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.ui.login.UserActivity;

public class MenuBack extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemenu);
        Button userListButton = findViewById(R.id.user);
        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, navigate to UserListActivity
                Intent intent = new Intent(MenuBack.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
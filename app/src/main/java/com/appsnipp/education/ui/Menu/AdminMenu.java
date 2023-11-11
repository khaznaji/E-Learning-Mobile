/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.R;
import com.appsnipp.education.ui.Complaints.ListComplaints;
import com.appsnipp.education.ui.Courses.ListCourses;
import com.appsnipp.education.ui.Events.ListEvent;
import com.appsnipp.education.ui.User.AdminUser;

public class AdminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemenu); // Set the layout XML for the UpdateEvent activity
        // You should have an eventupdate.xml layout file in your "res/layout" directory

    Button userButton = findViewById(com.appsnipp.education.R.id.user);
        Button eventButton = findViewById(com.appsnipp.education.R.id.event);
        Button coursesButton = findViewById(R.id.courses);
        Button complaintsButton = findViewById(R.id.complaints);

        // Ajouter un écouteur de clic au bouton
        userButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
            Intent intent = new Intent(AdminMenu.this, AdminUser.class);
            startActivity(intent);
        }
    });
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(AdminMenu.this, ListEvent.class);
                startActivity(intent);
            }
        }); coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(AdminMenu.this, ListCourses.class);
                startActivity(intent);
            }
        }); complaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(AdminMenu.this, ListComplaints.class);
                startActivity(intent);
            }
        });
}}

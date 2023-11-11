/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.R;
import com.appsnipp.education.ui.Events.AddEvent;
import com.appsnipp.education.ui.Events.ListEvent;
import com.appsnipp.education.ui.Events.UpdateEvent;

public class ListCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcourses);

        // Obtenir une référence au bouton "modifier" par son ID
        Button modifierButton = findViewById(com.appsnipp.education.R.id.modifier);
        Button ajouterButton = findViewById(com.appsnipp.education.R.id.add);

        // Ajouter un écouteur de clic au bouton
        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(ListCourses.this, UpdateCourses.class);
                startActivity(intent);
            }
        });
        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(ListCourses.this, AddCourses.class);
                startActivity(intent);
            }
        });
    }
}

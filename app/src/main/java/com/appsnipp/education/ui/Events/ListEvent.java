/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Events;

import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.R;

public class ListEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.appsnipp.education.R.layout.listevent);

        // Obtenir une référence au bouton "modifier" par son ID
        Button modifierButton = findViewById(com.appsnipp.education.R.id.modifier);
        Button ajouterButton = findViewById(com.appsnipp.education.R.id.button);

        // Ajouter un écouteur de clic au bouton
        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(ListEvent.this, UpdateEvent.class);
                startActivity(intent);
            }
        });
                ajouterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                        Intent intent = new Intent(ListEvent.this, AddEvent.class);
                        startActivity(intent);
                    }
                });
    }
}

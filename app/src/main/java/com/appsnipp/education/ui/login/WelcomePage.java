/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.appsnipp.education.R;

import androidx.appcompat.app.AppCompatActivity;
public class WelcomePage extends AppCompatActivity {

    Button join_us;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page); // Correction ici
        join_us = findViewById(R.id.join_us);
        login = findViewById(R.id.login);
        join_us.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SignupPage.class);
            startActivity(intent);
        });
        login.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SigninPage.class);
            startActivity(intent);
        });
    }
}

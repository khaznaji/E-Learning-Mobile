/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;
import android.content.Intent;
        import android.os.Bundle;

import com.appsnipp.education.Application;
import com.appsnipp.education.MainActivity;
import com.appsnipp.education.R;
        import com.appsnipp.education.SplashActivity;
import com.appsnipp.education.ui.menuhome.HomeCoursesFragment;

import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

public class SigninPage extends AppCompatActivity {
    TextView back;
    Button loginButton; // Assurez-vous d'importer la classe Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signin_page);

        back = findViewById(R.id.back);
        loginButton = findViewById(R.id.loginButton); // Assurez-vous d'ajouter le bouton à votre layout XML

        back.setOnClickListener(view -> finish());

        // Lorsque vous cliquez sur le bouton "Login", vous démarrez l'activité principale.
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class); // Assurez-vous de remplacer "MainActivity" par le nom de votre activité principale.
            startActivity(intent);
        });

    }
}

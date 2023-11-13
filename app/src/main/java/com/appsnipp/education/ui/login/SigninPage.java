/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.User;
import com.appsnipp.education.MainActivity;
import com.appsnipp.education.MenuBack;
import com.appsnipp.education.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mindrot.jbcrypt.BCrypt;

public class SigninPage extends AppCompatActivity {
    TextView back;
    Button loginButton; // Assurez-vous d'importer la classe Button
    Button forgotPassword;

   /* @Override
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
    }*/
   private AppDataBase appDatabase;
    private EditText editTextEmail, editTextPassword;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.signin_page);


       appDatabase = AppDataBase.getInstance(this);

       editTextEmail = findViewById(R.id.editTextEmail);
       editTextPassword = findViewById(R.id.editTextPassword);

       Button btnLogin = findViewById(R.id.loginButton);
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               loginUser();
           }
       });
       TextView back = findViewById(R.id.back);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Appeler finish() pour retourner à l'activité précédente
               finish();
           }
       });
       TextView forgotPassword = findViewById(R.id.forgotPassword);

       forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // Rediriger vers l'activité de réinitialisation de mot de passe
               Intent intent = new Intent(SigninPage.this, ForgotPassword.class);
               startActivity(intent);
           }
       });
   }


    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        AsyncTask.execute(() -> {
            User user = appDatabase.userDAO().getUserByEmail(email);

            runOnUiThread(() -> {
                if (user != null && BCrypt.checkpw(password, user.password)) {
                    // Le mot de passe est correct
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

                    if (email.equals("admin@gmail.com") && password.equals("admin")) {
                        // Rediriger vers la page du menu
                        Intent intent = new Intent(this, MenuBack.class); // Remplacez MenuPage par le nom de votre activité de menu
                        startActivity(intent);
                        finish();
                    } else {
                        // Si ce n'est pas l'admin, redirigez vers MainActivity
                        saveSession(user);
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    // Le mot de passe est incorrect
                    Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void saveSession(User user) {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("user_id", user.id);
        editor.putString("user_firstname", user.firstname);
        editor.putString("user_lastname", user.lastname);
        editor.putString("user_email", user.email);// ou utilisez le nom d'utilisateur, selon votre cas
        editor.apply();
    }


}

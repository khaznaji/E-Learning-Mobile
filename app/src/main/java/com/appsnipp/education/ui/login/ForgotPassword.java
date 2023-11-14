package com.appsnipp.education.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.Dao.UserDao;
import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.User;
import com.appsnipp.education.R;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.editTextEmail);
        resetPasswordButton = findViewById(R.id.passButton);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer l'e-mail saisi par l'utilisateur
                String email = emailEditText.getText().toString().trim();

                // Vérifier si l'e-mail est valide
                if (isValidEmail(email)) {
                    // Vérifier si l'e-mail existe dans la base de données
                    if (isEmailInDatabase(email)) {
                        // Si l'e-mail existe, passer à l'activité de réinitialisation du mot de passe
                        Intent intent = new Intent(ForgotPassword.this, AnswerActivity.class);
                        intent.putExtra("EMAIL", email);
                        startActivity(intent);
                    } else {
                        // Si l'e-mail n'existe pas, afficher un message d'erreur
                        Toast.makeText(ForgotPassword.this, "Email does not exist ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Si l'e-mail n'est pas valide, afficher un message d'erreur
                    Toast.makeText(ForgotPassword.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private boolean isEmailInDatabase(String email) {
        UserDao userDao = AppDataBase.getInstance(getApplicationContext()).userDAO();
        User user = userDao.getUserByEmail(email);
        return user != null; // Si user est différent de null, l'e-mail existe dans la base de données
    }
}

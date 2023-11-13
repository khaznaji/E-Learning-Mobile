package com.appsnipp.education.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.Dao.UserDao;
import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.R;
import com.appsnipp.education.Entity.User;

import org.mindrot.jbcrypt.BCrypt;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText passwordEditText, confirmPasswordEditText;
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        resetPasswordButton = findViewById(R.id.btnSignUp);

        String email = getIntent().getStringExtra("EMAIL");

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword) && password.equals(confirmPassword)) {
                    // Update the user's password in the database
                    if (updatePasswordInDatabase(email, password)) {
                        Toast.makeText(ResetPasswordActivity.this, "Mot de passe réinitialisé avec succès", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(ResetPasswordActivity.this, SigninPage.class);
                        startActivity(loginIntent);
                        finish();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Échec de la réinitialisation du mot de passe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean updatePasswordInDatabase(String email, String newPassword) {
        UserDao userDao = AppDataBase.getInstance(getApplicationContext()).userDAO();
        User user = userDao.getUserByEmail(email);


        if (user != null) {
            // Hash the new password
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            // Update the user's password with the hashed password
            user.setPassword(hashedPassword);
            userDao.update(user);
            return true;
        } else {
            return false;
        }
    }
}

    /*
     * Copyright (c) 2023. rogergcc
     */

    package com.appsnipp.education.ui.login;
    import android.content.Intent;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.view.View;
    import android.view.Window;
    import android.view.WindowManager;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.appsnipp.education.DataBase.AppDataBase;
    import com.appsnipp.education.Entity.User;
    import com.appsnipp.education.R;

    import androidx.appcompat.app.AppCompatActivity;

    import org.mindrot.jbcrypt.BCrypt;

    public class SignupPage extends AppCompatActivity {

        private AppDataBase appDatabase;
        private EditText editTextLastName, editTextFirstName, editTextEmail, editTextPassword, editTextConfirmPassword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup_page);

            appDatabase = AppDataBase.getInstance(this);

            editTextLastName = findViewById(R.id.editTextLastName);
            editTextFirstName = findViewById(R.id.editTextFirstName);
            editTextEmail = findViewById(R.id.editTextEmail);
            editTextPassword = findViewById(R.id.editTextPassword);
            editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

            Button btnSignUp = findViewById(R.id.btnSignUp);
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerUser();
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
        }

        private void registerUser() {
            String lastName = editTextLastName.getText().toString().trim();
            String firstName = editTextFirstName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            if (lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Vérifier si l'email existe déjà dans la base de données
            AsyncTask.execute(() -> {
                User existingUser = appDatabase.userDAO().getUserByEmail(email);

                runOnUiThread(() -> {
                    if (existingUser != null) {
                        // L'email est déjà utilisé
                        Toast.makeText(this, "Email already in use", Toast.LENGTH_SHORT).show();
                    } else {
                        // L'email n'est pas dans la base de données, créer un nouvel utilisateur
                        User newUser = new User();
                        newUser.lastname = lastName;
                        newUser.firstname = firstName;
                        newUser.email = email;
                        newUser.password = hashedPassword;

                        // Insérer l'utilisateur dans la base de données
                        insertUser(newUser);
                        System.out.println(lastName);
                    }
                });
            });
        }


        private void insertUser(User user) {
            AsyncTask.execute(() -> {
                user.image = String.valueOf(R.drawable.ic_user_icon);

                appDatabase.userDAO().insert(user);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

                    // Navigate to the login screen
                    Intent intent = new Intent(SignupPage.this, SigninPage.class);
                    startActivity(intent);
                    finish();  // Optional: finish the current activity if you don't want the user to navigate back to it
                });
            });
        }
    }
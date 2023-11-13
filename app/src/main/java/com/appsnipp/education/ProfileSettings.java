/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.appsnipp.education.Dao.UserDao;
import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.User;
import com.appsnipp.education.ui.login.WelcomePage;
import com.bumptech.glide.Glide;

public class ProfileSettings  extends AppCompatActivity {

    EditText emailEditText, firstNameEditText, lastNameEditText;
    private AppDataBase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_settings);
        emailEditText = findViewById(R.id.emailEditText);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);

        Button editButton = findViewById(R.id.btn_submit);
        int userId = getUserID();
        String firstName = getFirstName();
        String lastName = getLastName();
        String email = getEmail();

        appDatabase = AppDataBase.getInstance(this);

        // Afficher les données de l'utilisateur dans les champs
        emailEditText.setText(email);
        firstNameEditText.setText(firstName);
        lastNameEditText.setText(lastName);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText.setEnabled(true);
                firstNameEditText.setEnabled(true);
                lastNameEditText.setEnabled(true);

                String newEmail = emailEditText.getText().toString();
                String newFirstName = firstNameEditText.getText().toString();
                String newLastName = lastNameEditText.getText().toString();

                // Update user information in the Room database
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Get the userId from SharedPreferences
                        int userId = getUserID();

                        // Call the updateUser method in UserDao
                        int updateResult = appDatabase.userDAO().updateUser(userId, newEmail, newFirstName, newLastName);

                        // Assuming your update method returns the number of rows affected
                        boolean updateSuccessful = (updateResult > 0);

                        // Update UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (updateSuccessful) {
                                    // Update successful, handle UI or other actions
                                    // ...

                                    // Disable editing of the fields after the update
                                    emailEditText.setEnabled(false);
                                    firstNameEditText.setEnabled(false);
                                    lastNameEditText.setEnabled(false);
                                    Toast.makeText(ProfileSettings.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    // Update failed, handle UI or other actions
                                    // ...
                                }
                            }
                        });
                    }
                });
            }
        });
        Button deleteAccountButton = findViewById(R.id.btn_delete_account);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });
    }
    private void showDeleteConfirmationDialog() {


        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Supprimez le compte du current user
                        deleteUserAccount();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
    private void showFarewellNotification() {
        // Créez et affichez une notification simple
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Goodbye!")
                .setContentText("We hope to see you again soon.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }
    private void deleteUserAccount() {
        AsyncTask.execute(() -> {
            // Obtenez l'utilisateur actuel à partir de la base de données
            User currentUser = appDatabase.userDAO().getUserById(getUserID());

            if (currentUser != null) {
                // Supprimez l'utilisateur de la base de données Room
                appDatabase.userDAO().delete(currentUser);

                // Mettez à jour l'interface utilisateur sur le thread principal
                runOnUiThread(() -> {
                    // Redirigez l'utilisateur vers l'écran de connexion
                    startActivity(new Intent(ProfileSettings.this, WelcomePage.class));
                    finish(); // Terminez l'activité actuelle pour empêcher le retour à l'écran de profil
                    Toast.makeText(ProfileSettings.this, "GoodBye! We hope to see you again soon.", Toast.LENGTH_SHORT).show();

                });
            } else {
                Toast.makeText(ProfileSettings.this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private int getUserID() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getInt("user_id", -1);
    }


    private String getFirstName() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getString("user_firstname", null);
    }

    private String getLastName() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getString("user_lastname", null);
    }

    private String getEmail() {
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        return preferences.getString("user_email", null);
    }



}
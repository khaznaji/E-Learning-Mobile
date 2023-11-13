/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.Dao.UserDao;
import com.appsnipp.education.DataBase.AppDataBase;

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
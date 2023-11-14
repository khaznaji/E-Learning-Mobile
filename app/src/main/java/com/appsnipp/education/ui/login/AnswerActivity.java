/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.Dao.UserDao;
import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.User;
import com.appsnipp.education.R;

public class AnswerActivity extends AppCompatActivity {

    private EditText editTextAnswer1, editTextAnswer2;
    private Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_activity);

        editTextAnswer1 = findViewById(R.id.editTextAnswer1);
        editTextAnswer2 = findViewById(R.id.editTextAnswer2);
        validateButton = findViewById(R.id.validateButton);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("EMAIL");
                String answer1 = editTextAnswer1.getText().toString().trim();
                String answer2 = editTextAnswer2.getText().toString().trim();

                checkAnswers(email, answer1, answer2);
            }
        });
    }

    private void checkAnswers(String email, String answer1, String answer2) {
        UserDao userDao = AppDataBase.getInstance(getApplicationContext()).userDAO();
        User user = userDao.getUserByAnswers(email, answer1, answer2);

        if (user != null) {
            // Les réponses sont correctes, rediriger vers ResetPasswordActivity
            Intent intent = new Intent(AnswerActivity.this, ResetPasswordActivity.class);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
            finish();
        } else {
            // Les réponses sont incorrectes, afficher un message d'erreur
            Toast.makeText(AnswerActivity.this, "Incorrect answers", Toast.LENGTH_SHORT).show();
        }
    }
}

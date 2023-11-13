/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Complaints;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.R;
import com.appsnipp.education.complaint;
import com.appsnipp.education.database.AppDataBase;


import java.util.List;

public class AddArticle extends AppCompatActivity {
    Button saveButton;
    EditText userName,pwd;
    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaintadd);

        database= AppDataBase.getInstance(getApplicationContext());
        saveButton=findViewById(R.id.ComplaintAddBtn);
        userName=findViewById(R.id.ComplaintTitleInput);
        pwd=findViewById(R.id.ComplaintDescriptionInput);

        saveButton.setOnClickListener(v -> {
            complaint user = new complaint(userName.getText().toString(),pwd.getText().toString());
            database.userDAO().insertTodo(user);
            List<complaint> lu = database.userDAO().getAllTodos();
            for(complaint userList :lu ){
                System.out.println(userList);
            }

        });
    }
}
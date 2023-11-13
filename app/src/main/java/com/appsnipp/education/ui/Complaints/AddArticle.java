/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Complaints;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.R;
import com.appsnipp.education.Entity.complaint;
import com.appsnipp.education.Database.AppDataBase;
import com.appsnipp.education.ui.Courses.AddCourses;


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
            String title =userName.getText().toString();
            String description =pwd.getText().toString();
            complaint user = new complaint(userName.getText().toString(),pwd.getText().toString());
            if (title.isEmpty() || description.isEmpty() ) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;}else{
            database.userDAO().insertTodo(user);
            Toast.makeText(AddArticle.this,"Inserted",Toast.LENGTH_SHORT).show();

            List<complaint> lu = database.userDAO().getAllTodos();
            for(complaint userList :lu ){
                System.out.println(userList);
            }}

        });
    }
}
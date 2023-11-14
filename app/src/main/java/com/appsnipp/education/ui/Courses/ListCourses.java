/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Courses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.Courses;
import com.appsnipp.education.R;

import java.util.ArrayList;
import java.util.List;

public class ListCourses extends AppCompatActivity {
    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private CourseAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.cc);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        userAdapter = new CourseAdapter(new ArrayList<>());
        recyclerViewUsers.setAdapter(userAdapter);

        // Load the list of users in the background
        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<Courses> userList = appDatabase.userDAOoo().ListCourses();

            runOnUiThread(() -> {
                // Check if the list is empty
                if (userList.isEmpty()) {
                    // Display a message indicating that the table is empty
                    // You can customize this based on your UI requirements
                    // For simplicity, you can use a Toast or set a TextView's text
                    // In this example, I'm using a Toast
                    Toast.makeText(ListCourses.this, "Table is empty", Toast.LENGTH_SHORT).show();
                } else {
                    // Update the adapter with the new list of users
                    userAdapter.setUserList(userList);
                    // Call notifyDataSetChanged on the adapter
                    userAdapter.notifyDataSetChanged();
                }
            });
        });
    }
        public void deleteComplaint (Courses courses){
            AsyncTask.execute(() -> {
                // Delete the complaint from the database
                appDatabase.userDAOoo().deleteTodo(courses);

                // Reload the list of users after deletion
                loadUserList();

                runOnUiThread(() -> {
                    // Show a toast message or perform any UI update after deletion
                    Toast.makeText(ListCourses.this, "course deleted", Toast.LENGTH_SHORT).show();
                    loadUserList();
                });
                loadUserList();

            });
        }

    }

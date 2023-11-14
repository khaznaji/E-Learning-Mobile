/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Complaints;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.DataBase.AppDataBase;
import com.appsnipp.education.Entity.complaint;
import com.appsnipp.education.R;

import java.util.ArrayList;
import java.util.List;

public class ListComplaints extends AppCompatActivity {

    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private ComplaintAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_article_list);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.cc);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        userAdapter = new ComplaintAdapter(new ArrayList<>());
        recyclerViewUsers.setAdapter(userAdapter);

        // Load the list of users in the background
        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<complaint> userList = appDatabase.userDAO().getAllTodos();

            runOnUiThread(() -> {
                // Update the adapter with the new list of users
                userAdapter.setUserList(userList);
                // Call notifyDataSetChanged on the adapter
                userAdapter.notifyDataSetChanged();
            });
        });
    }
    // Add this method to handle deleting a complaint
    public void deleteComplaint(complaint complaint) {
        AsyncTask.execute(() -> {
            // Delete the complaint from the database
            appDatabase.userDAO().deleteTodo(complaint);

            // Reload the list of users after deletion
            loadUserList();

            runOnUiThread(() -> {
                // Show a toast message or perform any UI update after deletion
                Toast.makeText(ListComplaints.this, "Complaint deleted", Toast.LENGTH_SHORT).show();
            });
        });
}}
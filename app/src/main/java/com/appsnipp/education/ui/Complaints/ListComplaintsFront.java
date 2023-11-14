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

public class ListComplaintsFront extends AppCompatActivity {

    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private ComplaintAdapterFront userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list_front);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.cc);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        userAdapter = new ComplaintAdapterFront(new ArrayList<>());
        recyclerViewUsers.setAdapter(userAdapter);

        // Load the list of users in the background
        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<complaint> userList = appDatabase.userDAOK().getAllTodos();

            runOnUiThread(() -> {
                // Update the adapter with the new list of users
                userAdapter.setUserList(userList);
                // Call notifyDataSetChanged on the adapter
                userAdapter.notifyDataSetChanged();
            });
        });
    }
    // Add this method to handle deleting a complaint
 }
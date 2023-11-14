/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Forum;

import com.appsnipp.education.DataBase.AppDataBase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.appsnipp.education.Entity.Post;
import com.appsnipp.education.R;

import java.util.ArrayList;
import java.util.List;
public class ListForum  extends AppCompatActivity{

    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private ForumAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listposts);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.cc);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        userAdapter = new ForumAdapter(new ArrayList<>());
        recyclerViewUsers.setAdapter(userAdapter);

        // Load the list of users in the background
        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<Post> userList = appDatabase.postDao().getAllPosts();

            runOnUiThread(() -> {
                // Update the adapter with the new list of users
                userAdapter.setUserList(userList);
                // Call notifyDataSetChanged on the adapter
                userAdapter.notifyDataSetChanged();
            });
        });
    }
    // Add this method to handle deleting a complaint
    public void deleteComplaint(Post complaint) {
        AsyncTask.execute(() -> {
            // Delete the complaint from the database
            appDatabase.postDao().delete(complaint);

            // Reload the list of users after deletion
            loadUserList();

            runOnUiThread(() -> {
                // Show a toast message or perform any UI update after deletion
                Toast.makeText(ListForum.this, "Complaint deleted", Toast.LENGTH_SHORT).show();
            });
        });
    }}

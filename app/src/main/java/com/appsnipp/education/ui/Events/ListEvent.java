/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Events;

//import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Database.AppDataBase;
//import com.appsnipp.education.Entity.complaint;
import com.appsnipp.education.R;

import java.util.ArrayList;
import java.util.List;

public class ListEvent extends AppCompatActivity {
    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private EventAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.appsnipp.education.R.layout.listevent);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.ccc);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        userAdapter = new EventAdapter(new ArrayList<>());
        recyclerViewUsers.setAdapter(userAdapter);

        // Load the list of users in the background
        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<Event> userList = appDatabase.userDAO().getAllTodos();

            runOnUiThread(() -> {
                // Update the adapter with the new list of users
                userAdapter.setUserList(userList);
                // Call notifyDataSetChanged on the adapter
                userAdapter.notifyDataSetChanged();
            });
        });
    }
}
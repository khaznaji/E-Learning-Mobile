/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;

import com.appsnipp.education.Entity.User;
import com.appsnipp.education.R;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.DataBase.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        userAdapter = new UserAdapter(new ArrayList<>());
        recyclerViewUsers.setAdapter(userAdapter);

        // Load the list of users in the background
        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<User> userList = appDatabase.userDAO().getAllUsers();
            List<User> filteredList = new ArrayList<>();
            for (User user : userList) {
                if (!user.getEmail().equals("admin@gmail.com")) {
                    filteredList.add(user);
                }
            }
            runOnUiThread(() -> {
                // Update the adapter with the filtered list of users
                userAdapter.setUserList(filteredList);
                // Call notifyDataSetChanged on the adapter
                userAdapter.notifyDataSetChanged();
            });
        });
    }

}
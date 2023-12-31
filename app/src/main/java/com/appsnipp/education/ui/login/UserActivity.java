package com.appsnipp.education.ui.login;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.Entity.User;
import com.appsnipp.education.R;
import com.appsnipp.education.DataBase.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {


    private AppDataBase appDatabase;
    private RecyclerView recyclerViewUsers;
    private UserAdapter userAdapter;
    private List<User> originalUserList; // To store the original user list for filtering
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        appDatabase = AppDataBase.getInstance(this);

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(new ArrayList<>(), (position, view) -> showDeleteConfirmationDialog(position));
        recyclerViewUsers.setAdapter(userAdapter);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterUserList(newText);
                return true;
            }
        });

        loadUserList();
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            List<User> userList = appDatabase.userDAO().getAllUsers();
            originalUserList = new ArrayList<>(userList); // Save the original list for filtering

            List<User> filteredList = new ArrayList<>();
            for (User user : userList) {
                if (!user.getEmail().equals("admin@gmail.com")) {
                    filteredList.add(user);
                }
            }

            runOnUiThread(() -> {
                userAdapter.setUserList(filteredList);
                userAdapter.notifyDataSetChanged();
            });
        });
    }

    private void filterUserList(String query) {
        List<User> filteredUsers = new ArrayList<>();

        for (User user : originalUserList) {
            if (user.getEmail().toLowerCase().contains(query.toLowerCase())) {
                filteredUsers.add(user);
            }
        }

        userAdapter.setUserList(filteredUsers);
        userAdapter.notifyDataSetChanged();
    }

    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes", (dialog, which) -> onDeleteConfirmed(position))
                .setNegativeButton("No", null)
                .show();
    }

    private void onDeleteConfirmed(int position) {
        User clickedUser = userAdapter.getUserList().get(position);

        AsyncTask.execute(() -> appDatabase.userDAO().delete(clickedUser));

        userAdapter.getUserList().remove(position);
        userAdapter.notifyItemRemoved(position);

        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
    }
}

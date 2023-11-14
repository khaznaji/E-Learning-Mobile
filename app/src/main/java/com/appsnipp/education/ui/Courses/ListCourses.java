/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Courses;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.R;
import com.appsnipp.education.Room.Courses;
import com.appsnipp.education.Room.CoursesDao;
import com.appsnipp.education.Room.CoursesDatabase;
import com.appsnipp.education.ui.adapter.CAdapter;
import com.appsnipp.education.ui.adapter.CoursesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListCourses extends AppCompatActivity {
    private CoursesDatabase coursesDatabase;
private CoursesDao coursesDao;
    private RecyclerView recyclerViewc;
    private CAdapter cAdapter;

    private CoursesAdapter coursesAdapter;



 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcourses);

        // Obtenir une référence au bouton "modifier" par son ID
        Button modifierButton = findViewById(com.appsnipp.education.R.id.modifier);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button ajouterButton = findViewById(com.appsnipp.education.R.id.add);

        // Ajouter un écouteur de clic au bouton
        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(ListCourses.this, UpdateCourses.class);
                startActivity(intent);
            }
        });
        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers "eventupdate.xml" en créant une nouvelle intention (Intent)
                Intent intent = new Intent(ListCourses.this, AddCourses.class);
                startActivity(intent);
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_list);

        coursesDatabase = CoursesDatabase.getInstance(this);

        recyclerViewc = findViewById(R.id.cc);
        recyclerViewc.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (you can load the data later)
        cAdapter = new CAdapter(new ArrayList<>());
        recyclerViewc.setAdapter(cAdapter);

        // Load the list of users in the background
        loadUserList();
        fetchData();

    }
    private void fetchData(){
        List<Courses> coursesList=coursesDao.ListCourses();
        for(int i=0;i<coursesList.size();i++){

            Courses courses=coursesList.get(i);
            coursesAdapter.addCourses(courses);

        }
    }

    private void loadUserList() {
        AsyncTask.execute(() -> {
            // Load the list of users from the database
            List<Courses> userList = coursesDatabase.getDao().ListCourses();

            runOnUiThread(() -> {
                // Update the adapter with the new list of users
                cAdapter.setKitaList(userList);;
                // Call notifyDataSetChanged on the adapter
                cAdapter.notifyDataSetChanged();
            });
        });
        fetchData();
    }



    // Add this method to handle deleting a complaint




}

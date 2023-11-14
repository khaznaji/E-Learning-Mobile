/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Courses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.R;
import com.appsnipp.education.Room.Courses;
import com.appsnipp.education.Room.CoursesDao;
import com.appsnipp.education.Room.CoursesDatabase;
import com.appsnipp.education.ui.adapter.AdapterListener;
import com.appsnipp.education.ui.adapter.CoursesAdapter;

import java.util.List;

public class AddCourses extends AppCompatActivity implements AdapterListener {

    EditText titleEd,descriptionEd,levelEd,durationEd,categoryEd;
    Button insertBtn;
private CoursesDatabase coursesDatabase ;
private CoursesDao coursesDao;
private CoursesAdapter coursesAdapter;
RecyclerView myrecycler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourses);
        coursesDatabase=CoursesDatabase.getInstance(this);
        coursesDao=coursesDatabase.getDao();
        descriptionEd=findViewById(R.id.Description);
        titleEd=findViewById(R.id.title);
        levelEd=findViewById(R.id.level);
        durationEd=findViewById(R.id.duration);
        categoryEd=findViewById(R.id.category);
        insertBtn=findViewById(R.id.add);
        myrecycler=findViewById(R.id.coursesRecycler);
        coursesAdapter=new CoursesAdapter(this,this);
myrecycler.setAdapter(coursesAdapter);
myrecycler.setLayoutManager(new LinearLayoutManager(this));

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String description=descriptionEd.getText().toString();
                String title=titleEd.getText().toString();
                String level=levelEd.getText().toString();
                String duration=durationEd.getText().toString();
                String category=categoryEd.getText().toString();
                Courses courses = new Courses(0,description.toString(),title.toString(),level.toString(),duration.toString(),category.toString());
                if (description.isEmpty() || title.isEmpty() || level.isEmpty() || duration.isEmpty() || category.isEmpty() ) {
                    Toast.makeText( AddCourses.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;}
                else{

                coursesAdapter.addCourses(courses);
                coursesDao.AddCourses(courses);}
                descriptionEd.setText("");
                titleEd.setText("");
                levelEd.setText("");
                durationEd.setText("");
                categoryEd.setText("");
//                Toast.makeText(AddCourses.this,"Inserted",Toast.LENGTH_SHORT).show();
                String message = "Inserted";
                SpannableString spannableString = new SpannableString(message);
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, message.length(), 0);

                Toast.makeText(AddCourses.this, spannableString, Toast.LENGTH_SHORT).show();






            }
        }



        );


    }
private void fetchData(){
    List<Courses> coursesList=coursesDao.ListCourses();
    for(int i=0;i<coursesList.size();i++){

        Courses courses=coursesList.get(i);
        coursesAdapter.addCourses(courses);

    }
}

    @Override
    public void OnUpdate(Courses courses ) {
        Intent intent= new Intent( this,UpdateCourses.class);
        intent.putExtra("mode",courses);
        startActivity(intent);
        fetchData();


    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}

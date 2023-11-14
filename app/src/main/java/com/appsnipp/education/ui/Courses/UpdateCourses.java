/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Courses;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

import com.appsnipp.education.R;
import com.appsnipp.education.Room.Courses;
import com.appsnipp.education.Room.CoursesDao;
import com.appsnipp.education.Room.CoursesDatabase;

public class UpdateCourses extends AppCompatActivity {
  private  EditText titleEd,descriptionEd,levelEd,durationEd,categoryEd;
   private Button update;
private Courses courses;

private CoursesDatabase coursesDatabase;
private CoursesDao coursesDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatecourses);

        coursesDatabase=CoursesDatabase.getInstance(this);
        coursesDao=coursesDatabase.getDao();


        descriptionEd=findViewById(R.id.Description);
        titleEd=findViewById(R.id.title);
        levelEd=findViewById(R.id.level);
        durationEd=findViewById(R.id.duration);
        categoryEd=findViewById(R.id.category);
        update=findViewById(R.id.add);
courses=(Courses) getIntent().getSerializableExtra("mode");

        titleEd.setText(courses.getTitle());
        descriptionEd.setText(courses.getDescription());
        levelEd.setText(courses.getLevel());
        durationEd.setText(courses.getDuration());
        categoryEd.setText(courses.getCategory());

        update.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view) {
                        Courses coursesModel= new Courses(courses.getId(),titleEd.getText().toString(),descriptionEd.getText().toString(),levelEd.getText().toString(),durationEd.getText().toString(),categoryEd.getText().toString());
coursesDao.UpdateCourses(coursesModel);
finish();




                    }

                }



        );

    }
}

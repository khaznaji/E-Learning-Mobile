package com.appsnipp.education.ui.Courses;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.education.R;
import com.appsnipp.education.Entity.Courses;
import com.appsnipp.education.Dao.CoursesDao;
import com.appsnipp.education.DataBase.AppDataBase;
//import com.appsnipp.education.ui.adapter.CoursesAdapter;


public class AddCourses extends AppCompatActivity {

    EditText titleEd,descriptionEd,levelEd,durationEd,categoryEd;
    Button insertBtn;
    private AppDataBase coursesDatabase ;
    private CoursesDao coursesDao;
  //  private CoursesAdapter coursesAdapter;
    RecyclerView myrecycler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourses);
        coursesDatabase=AppDataBase.getInstance(this);
        coursesDao=coursesDatabase.userDAOoo();
        descriptionEd=findViewById(R.id.Description);
        titleEd=findViewById(R.id.title);
        levelEd=findViewById(R.id.level);
        durationEd=findViewById(R.id.duration);
        categoryEd=findViewById(R.id.category);
        insertBtn=findViewById(R.id.add);
//        myrecycler=findViewById(R.id.coursesRecycler);
//        coursesAdapter=new CoursesAdapter(this);
//        myrecycler.setAdapter(coursesAdapter);
//        myrecycler.setLayoutManager(new LinearLayoutManager(this));
//
//        fetchData();
        insertBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             String description=descriptionEd.getText().toString();
                                             String title=titleEd.getText().toString();
                                             String level=levelEd.getText().toString();
                                             String duration=durationEd.getText().toString();
                                             String category=categoryEd.getText().toString();

                                             Courses courses = new Courses(0,description,title,level,duration,category);

                                             //coursesAdapter.addCourses(courses);
                                             coursesDao.AddCourses(courses);
                                             descriptionEd.setText("");
                                             titleEd.setText("");
                                             levelEd.setText("");
                                             durationEd.setText("");
                                             categoryEd.setText("");

                                             Toast.makeText(AddCourses.this,"Inserted",Toast.LENGTH_SHORT).show();






                                         }
                                     }



        );


    }
//    private void fetchData(){
//        List<Courses> coursesList=coursesDao.ListCourses();
//        for(int i=0;i<coursesList.size();i++){
//
//            Courses courses=coursesList.get(i);
//            coursesAdapter.addCourses(courses);
//
//        }
//    }

}
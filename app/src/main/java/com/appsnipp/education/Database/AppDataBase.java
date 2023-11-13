
        package com.appsnipp.education.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.appsnipp.education.Dao.CoursesDao;
import com.appsnipp.education.Dao.EventDao;
import com.appsnipp.education.Entity.Courses;
import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.Entity.complaint;
import com.appsnipp.education.Dao.complaintDao;

@Database(entities = {complaint.class, Event.class, Courses.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance ;
    // a tt les DAO's
    public abstract complaintDao userDAO();
    public abstract EventDao userDAOo();
    public abstract CoursesDao userDAOoo();


    public static AppDataBase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "educationnn")
                    .allowMainThreadQueries()
                    .build();

        }

        return instance;
    }
}
/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Courses.class}, version = 2)
public abstract class CoursesDatabase extends RoomDatabase {

public abstract CoursesDao getDao();
public static CoursesDatabase INSTANCE ;

public static CoursesDatabase getInstance(Context context ) {
    if (INSTANCE==null) {
        INSTANCE= Room.databaseBuilder(context,CoursesDatabase.class,"CoursesDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
    return INSTANCE;
}


}

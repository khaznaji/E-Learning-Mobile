/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.appsnipp.education.Dao.EventDao;
import com.appsnipp.education.Entity.Event;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance ;
    // a tt les DAO's
    public abstract EventDao userDAO();

    public static AppDataBase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "education")
                    .allowMainThreadQueries()
                    .build();

        }

        return instance;
    }
}